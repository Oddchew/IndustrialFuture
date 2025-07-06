package by.oddchew.IndustrialFuture.blockentity;

import by.oddchew.IndustrialFuture.blockentity.custom.AbstractMachineBlockEntity;
import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import by.oddchew.IndustrialFuture.menu.MachineMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY;

public class MachineBlockEntity extends AbstractMachineBlockEntity {
    private static final String translationKey = "block.industrial_future.machine";
    private static final int inventorySize = 2;

    public MachineBlockEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.MACHINE.get(), pos, state, inventorySize, 10, translationKey);
    }

    @Override
    public AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new MachineMenu(id, inv, this);
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide) {
            var ref = new Object() {
                boolean dirty = false;
            };
            boolean wasBurning = state.getValue(AbstractMachineBlock.BURNING);

            // Генерация энергии из топлива
            if (this.burnTime > 0) {
                this.burnTime--;
                this.energyStorage.receiveEnergy(receivedEnergy, false);
                ref.dirty = true;
            }

            // Проверка топлива
            boolean canStoreEnergy = this.energyStorage.getEnergyStored() < this.energyStorage.getMaxEnergyStored();
            boolean canTransferToNeighbors = canTransferToNeighbors(level, pos);
            if (this.burnTime <= 0 && (canStoreEnergy || canTransferToNeighbors)) {
                ItemStack fuel = this.inventory.getStackInSlot(0);
                if (!fuel.isEmpty()) {
                    int burnTime = ForgeHooks.getBurnTime(fuel, null);
                    if (burnTime > 0) {
                        this.burnTime = burnTime;
                        this.totalBurnTime = burnTime;
                        fuel.shrink(1);
                        ref.dirty = true;
                    }
                }
            }

            // Передача энергии в батарею
            ItemStack battery = this.inventory.getStackInSlot(1);
            if (!battery.isEmpty() && this.energyStorage.getEnergyStored() > 0) {
                LazyOptional<IEnergyStorage> itemEnergyCap = battery.getCapability(ENERGY);
                itemEnergyCap.ifPresent(itemEnergy -> {
                    int energyToTransfer = this.energyStorage.extractEnergy(100, true);
                    int energyAccepted = itemEnergy.receiveEnergy(energyToTransfer, false);
                    this.energyStorage.extractEnergy(energyAccepted, false);
                    if (energyAccepted > 0) {
                        ref.dirty = true;
                    }
                });
            }

            // Обновление состояния горения
            boolean isBurning = this.burnTime > 0;
            if (wasBurning != isBurning) {
                state = state.setValue(AbstractMachineBlock.BURNING, isBurning);
                level.setBlock(pos, state, 3);
                ref.dirty = true;
            }

            if (ref.dirty) {
                setChanged();
                level.sendBlockUpdated(pos, state, state, 3);
            }

            // Передача энергии соседям
            super.tick(level, pos, state);
        }
    }

    private boolean canTransferToNeighbors(Level level, BlockPos pos) {
        for (Direction direction : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(direction));
            if (neighbor != null && neighbor != this) {
                LazyOptional<IEnergyStorage> neighborEnergyCap = neighbor.getCapability(ENERGY, direction.getOpposite());
                if (neighborEnergyCap.isPresent()) {
                    IEnergyStorage neighborEnergy = neighborEnergyCap.orElse(null);
                    if (neighborEnergy != null && neighborEnergy.canReceive()) {
                        return true;
                    }
                }
            }
        }
        return false;
    }
}