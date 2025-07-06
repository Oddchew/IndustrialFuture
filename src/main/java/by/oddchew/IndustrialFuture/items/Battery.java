package by.oddchew.IndustrialFuture.items;

import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.chat.Component;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.TooltipFlag;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.capabilities.ICapabilityProvider;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class Battery extends Item {

    private static final int MAX_ENERGY = 10000; // Максимальная энергия: 10000 FE
    private static final int MAX_TRANSFER = 100; // Максимальная скорость передачи: 100 FE/t
    private int initialEnergy = 0;

    public Battery(Properties properties, int initialCharge) {
        super(properties.stacksTo(1));
        initialEnergy = initialCharge;
    }

    // Добавляем поддержку энергии через Capability
    @Override
    public @Nullable ICapabilityProvider initCapabilities(ItemStack stack, @Nullable CompoundTag nbt) {
        return new ICapabilityProvider() {
            private final EnergyStorage energyStorage = new EnergyStorage(MAX_ENERGY, MAX_TRANSFER, MAX_TRANSFER, initialEnergy) {
                @Override
                public int receiveEnergy(int maxReceive, boolean simulate) {
                    int received = super.receiveEnergy(maxReceive, simulate);
                    if (!simulate) {
                        // Сохраняем новое значение энергии в NBT предмета
                        stack.getOrCreateTag().putInt("Energy", getEnergyStored());
                    }
                    return received;
                }

                @Override
                public int extractEnergy(int maxExtract, boolean simulate) {
                    int extracted = super.extractEnergy(maxExtract, simulate);
                    if (!simulate) {
                        // Сохраняем новое значение энергии в NBT предмета
                        stack.getOrCreateTag().putInt("Energy", getEnergyStored());
                    }
                    return extracted;
                }
            };

            private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> {
                // Загружаем энергию из NBT при создании
                if (stack.hasTag() && stack.getTag().contains("Energy")) {
                    int savedEnergy = stack.getTag().getInt("Energy");
                    try {
                        java.lang.reflect.Field energyField = EnergyStorage.class.getDeclaredField("energy");
                        energyField.setAccessible(true);
                        energyField.setInt(energyStorage, Math.min(savedEnergy, energyStorage.getMaxEnergyStored()));
                    } catch (Exception e) {
                        // Если отражение не сработало, используем receiveEnergy
                        while (energyStorage.getEnergyStored() > 0) {
                            energyStorage.extractEnergy(energyStorage.getMaxEnergyStored(), false);
                        }
                        energyStorage.receiveEnergy(savedEnergy, false);
                    }
                }
                return energyStorage;
            });

            @Override
            public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable net.minecraft.core.Direction side) {
                if (cap == ForgeCapabilities.ENERGY) {
                    return energyHandler.cast();
                }
                return LazyOptional.empty();
            }

            // Уничтожаем LazyOptional при удалении предмета
            public void invalidate() {
                energyHandler.invalidate();
            }
        };
    }

    // Добавляем всплывающую подсказку с текущим количеством энергии
    @Override
    public void appendHoverText(ItemStack stack, @Nullable Level level, List<Component> tooltip, TooltipFlag flag) {
        stack.getCapability(ForgeCapabilities.ENERGY).ifPresent(energy -> {
            int energyStored = energy.getEnergyStored();
            int maxEnergy = energy.getMaxEnergyStored();
            tooltip.add(Component.literal("Energy: " + energyStored + "/" + maxEnergy + " FE"));
        });
    }

    // Указываем, что предмет имеет "энергию" (для визуального эффекта в инвентаре, например, полоску прочности)
    @Override
    public boolean isBarVisible(ItemStack stack) {
        return true;
    }

    @Override
    public int getBarWidth(ItemStack stack) {
        return stack.getCapability(ForgeCapabilities.ENERGY)
                .map(energy -> Math.round((float) energy.getEnergyStored() * 13 / energy.getMaxEnergyStored()))
                .orElse(0);
    }

    @Override
    public int getBarColor(ItemStack stack) {
        return 0x00FF00; // Зелёный цвет для индикатора энергии
    }
}