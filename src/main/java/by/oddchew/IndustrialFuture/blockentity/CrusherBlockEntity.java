package by.oddchew.IndustrialFuture.blockentity;

import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import by.oddchew.IndustrialFuture.blockentity.logic.CrusherLogic;
import by.oddchew.IndustrialFuture.menu.CrusherMenu;
import by.oddchew.IndustrialFuture.slot.SlotCrushed;
import by.oddchew.IndustrialFuture.blockentity.custom.AbstractMachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import java.lang.reflect.Field;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY;

public class CrusherBlockEntity extends AbstractMachineBlockEntity {
    private static final int ENERGY_PER_TICK = 2;
    private static final int ENERGY_TRANSFER_PER_TICK = 100;
    private static final CrusherLogic crusherLogic = new CrusherLogic();
    private static final String translationKey = "block.industrial_future.crusher";
    private static final int inventorySize = 3;

    public CrusherBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.CRUSHER.get(), pos, state, inventorySize, 0, translationKey);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new CrusherMenu(id, inv, this);
    }

    public int getCrushProgressScaled() {
        return progress * 11 / maxProgress;
    }

    @Override
    protected boolean canExtractEnergy() {
        return false;
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide) {
            var ref = new Object() {
                boolean dirty = false;
            };

            // Прием энергии от соседей
            super.tick(level, pos, state);

            // Перенос энергии из батареи
            ItemStack battery = inventory.getStackInSlot(1);
            if (!battery.isEmpty()) {
                LazyOptional<IEnergyStorage> batteryEnergyCap = battery.getCapability(ENERGY);
                batteryEnergyCap.ifPresent(batteryEnergy -> {
                    int energyExtracted = batteryEnergy.extractEnergy(ENERGY_TRANSFER_PER_TICK, true);
                    if (energyExtracted > 0) {
                        int energyReceived = energyStorage.receiveEnergy(energyExtracted, false);
                        batteryEnergy.extractEnergy(energyReceived, false);
                        if (energyReceived > 0) {
                            ref.dirty = true;
                        }
                    }
                });
            }

            // Проверка дробления
            ItemStack input = inventory.getStackInSlot(0);
            ItemStack output = inventory.getStackInSlot(2);
            boolean canCrush = !input.isEmpty() && canCrush(input) && crusherLogic.ElementMatch(input, output);

            // Сброс дробления
            if (input.isEmpty()) {
                progress = 0;
            }

            // Дробление
            if (canCrush && progress < maxProgress && energyStorage.getEnergyStored() >= ENERGY_PER_TICK) {
                int energyExtracted = internalExtractEnergy(ENERGY_PER_TICK, false);
                if (energyExtracted == ENERGY_PER_TICK) {
                    progress++;
                    ref.dirty = true;
                }
            }

            // Завершение дробления
            if (progress >= maxProgress) {
                progress = 0;
                if (!input.isEmpty()) {
                    crusherLogic.CreatingDust(inventory);
                    inventory.getStackInSlot(0).shrink(1);
                }
                ref.dirty = true;
            }

            // Обновление состояния BURNING
            boolean isBurning = progress > 0;
            if (state.getValue(AbstractMachineBlock.BURNING) != isBurning) {
                level.setBlock(pos, state.setValue(AbstractMachineBlock.BURNING, isBurning), 3);
                ref.dirty = true;
            }

            // Синхронизация
            if (ref.dirty) {
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
            }
        }
    }

    private boolean canCrush(ItemStack stack) {
        return new SlotCrushed(inventory, 0, 0, 0).mayPlace(stack);
    }

}