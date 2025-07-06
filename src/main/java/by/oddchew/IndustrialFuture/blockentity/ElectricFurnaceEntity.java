package by.oddchew.IndustrialFuture.blockentity;

import by.oddchew.IndustrialFuture.blockentity.custom.AbstractMachineBlockEntity;
import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import by.oddchew.IndustrialFuture.menu.ElectricFurnaceMenu;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.item.crafting.SmeltingRecipe;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public class ElectricFurnaceEntity extends AbstractMachineBlockEntity {
    private static final int INVENTORY_SIZE = 3; // 0 - вход, 1 - батарея, 2 - выход
    private static final String TRANSLATION_KEY = "block.industrial_future.electric_furnace";
    private static final int ENERGY_TRANSFER_PER_TICK = 100;
    private static final int ENERGY_PER_TICK = 2;
    private static final int MAX_PROGRESS = 200; // 200 тиков ~ 10 секунд

    public ElectricFurnaceEntity(BlockPos pos, BlockState state) {
        super(ModBlockEntities.ELECRTIC_FURNACE.get(), pos, state, INVENTORY_SIZE, 0, TRANSLATION_KEY);
        this.maxProgress = MAX_PROGRESS; // Устанавливаем максимальное время плавки
    }

    @Override
    public @Nullable AbstractContainerMenu createMenu(int id, Inventory inv, Player player) {
        return new ElectricFurnaceMenu(id, inv, this, player.level);
    }

    @Override
    protected boolean canExtractEnergy() {
        return false; // Печь не отдает энергию
    }

    @Override
    public void tick(Level level, BlockPos pos, BlockState state) {
        if (level.isClientSide) return;

        var ref = new Object() {
            boolean dirty = false;
        };

        // Отключаем вызов super.tick, так как он не нужен для печи
        // super.tick(level, pos, state); // Убрано, чтобы избежать ненужной передачи энергии соседям

        // Перенос энергии из батареи
        ItemStack battery = inventory.getStackInSlot(1);
        if (!battery.isEmpty()) {
            LazyOptional<IEnergyStorage> batteryEnergyCap = battery.getCapability(ForgeCapabilities.ENERGY);
            batteryEnergyCap.ifPresent(batteryEnergy -> {
                int energyExtracted = batteryEnergy.extractEnergy(ENERGY_TRANSFER_PER_TICK, true);
                if (energyExtracted > 0) {
                    int energyReceived = energyStorage.receiveEnergy(energyExtracted, false);
                    if (energyReceived > 0) {
                        batteryEnergy.extractEnergy(energyReceived, false);
                        ref.dirty = true;
                    }
                }
            });
        }

        ItemStack input = inventory.getStackInSlot(0);
        ItemStack output = inventory.getStackInSlot(2);

        // Сброс прогресса, если вход пуст
        if (input.isEmpty()) {
            progress = 0;
        } else {
            // Проверка рецепта плавки
            Optional<SmeltingRecipe> recipe = level.getRecipeManager().getRecipeFor(
                    RecipeType.SMELTING, new SimpleContainer(input), level);
            if (recipe.isPresent() && canSmelt(recipe.get(), input, output)) {
                // Плавление с использованием энергии
                if (progress < maxProgress && energyStorage.getEnergyStored() >= ENERGY_PER_TICK) {
                    int energyExtracted = internalExtractEnergy(ENERGY_PER_TICK, false);
                    if (energyExtracted == ENERGY_PER_TICK) {
                        progress++;
                        ref.dirty = true;
                    }
                }

                // Завершение плавления
                if (progress >= maxProgress) {
                    progress = 0;
                    smeltItem(recipe.get(), input, output);
                    ref.dirty = true;
                }
            } else {
                progress = 0; // Сбрасываем прогресс, если рецепт не найден
            }
        }

        // Обновление состояния BURNING
        boolean isBurning = progress > 0 && energyStorage.getEnergyStored() >= ENERGY_PER_TICK;
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

    private boolean canSmelt(SmeltingRecipe recipe, ItemStack input, ItemStack output) {
        if (input.isEmpty() || !recipe.getIngredients().get(0).test(input)) return false;
        ItemStack result = recipe.getResultItem();
        if (result.isEmpty()) return false;
        if (output.isEmpty()) return true;
        return output.getItem() == result.getItem() && output.getCount() + result.getCount() <= output.getMaxStackSize();
    }

    private void smeltItem(SmeltingRecipe recipe, ItemStack input, ItemStack output) {
        ItemStack result = recipe.getResultItem();
        if (output.isEmpty()) {
            inventory.setStackInSlot(2, result.copy());
        } else if (output.getItem() == result.getItem()) {
            output.grow(result.getCount());
        }
        input.shrink(1);
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("Progress", progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        progress = tag.getInt("Progress");
    }

    /*@Override
    protected int internalExtractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energyStorage.getEnergyStored(), Math.min(100, maxExtract));
        if (!simulate) {
            energyStorage.extractEnergy(energyExtracted, false); // Используем стандартный метод
        }
        return energyExtracted;
    }*/
}