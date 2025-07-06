package by.oddchew.IndustrialFuture.blockentity.custom;

import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.network.Connection;
import net.minecraft.network.protocol.game.ClientboundBlockEntityDataPacket;
import net.minecraft.world.MenuProvider;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.network.chat.Component;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.EnergyStorage;
import net.minecraftforge.energy.IEnergyStorage;
import net.minecraftforge.items.ItemStackHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Field;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY;

public abstract class AbstractMachineBlockEntity extends BlockEntity implements MenuProvider {
    protected final ItemStackHandler inventory;
    protected final EnergyStorage energyStorage;
    protected final LazyOptional<EnergyStorage> energyHandler;
    protected int burnTime = 0;
    protected int totalBurnTime = 0;
    protected final int receivedEnergy;
    protected final String translationKey;
    protected int progress = 0;
    protected static final int maxEnergy = 10000;
    protected int maxProgress = 200;

    public AbstractMachineBlockEntity(BlockEntityType<?> type, BlockPos pos, BlockState state, int inventorySize, int receivedEnergy, String translationKey) {
        super(type, pos, state);
        this.inventory = new ItemStackHandler(inventorySize) {
            @Override
            protected void onContentsChanged(int slot) {
                setChanged();
            }
        };
        this.energyStorage = new EnergyStorage(maxEnergy, 100, 100) {
            @Override
            public int receiveEnergy(int maxReceive, boolean simulate) {
                int received = super.receiveEnergy(maxReceive, simulate);
                if (!simulate && received > 0) {
                    setChanged();
                }
                return received;
            }

            @Override
            public int extractEnergy(int maxExtract, boolean simulate) {
                int extracted = super.extractEnergy(maxExtract, simulate);
                if (!simulate && extracted > 0) {
                    setChanged();
                }
                return extracted;
            }

            @Override
            public boolean canExtract() {
                return canExtractEnergy();
            }

            @Override
            public boolean canReceive() {
                return true;
            }
        };
        this.energyHandler = LazyOptional.of(() -> energyStorage);
        this.receivedEnergy = receivedEnergy;
        this.translationKey = translationKey;
    }

    protected boolean canExtractEnergy() {
        return true; // По умолчанию блоки могут отдавать энергию
    }

    @Override
    public Component getDisplayName() {
        return Component.translatable(translationKey);
    }

    @Nullable
    @Override
    public abstract AbstractContainerMenu createMenu(int id, Inventory inv, Player player);

    public int getEnergyStored() {
        return energyStorage.getEnergyStored();
    }

    public int getMaxEnergy() {
        return energyStorage.getMaxEnergyStored();
    }

    public int getBurnProgressScaled() {
        if (totalBurnTime == 0) return 0;
        return burnTime * 14 / totalBurnTime;
    }

    public int getProgressScaled() {
        if (progress == 0) return 0;
        return progress * 14 / maxProgress;
    }

    public ItemStackHandler getInventory() {
        return inventory;
    }

    protected void transferEnergyToNeighbors(Level level, BlockPos pos) {
        if (energyStorage.getEnergyStored() <= 0 || !canExtractEnergy()) {
            return;
        }

        for (Direction direction : Direction.values()) {
            BlockEntity neighbor = level.getBlockEntity(pos.relative(direction));
            if (neighbor != null && neighbor != this) {
                LazyOptional<IEnergyStorage> neighborEnergyCap = neighbor.getCapability(ENERGY, direction.getOpposite());
                if (neighborEnergyCap.isPresent()) {
                    neighborEnergyCap.ifPresent(neighborEnergy -> {
                        if (neighborEnergy.canReceive()) {
                            int energyToTransfer = Math.min(energyStorage.getEnergyStored(), 100);
                            int energySimulated = energyStorage.extractEnergy(energyToTransfer, true);
                            if (energySimulated > 0) {
                                int energyAccepted = neighborEnergy.receiveEnergy(energySimulated, false);
                                if (energyAccepted > 0) {
                                    energyStorage.extractEnergy(energyAccepted, false);
                                    setChanged();
                                    neighbor.setChanged();
                                }
                            }
                        }
                    });
                }
            }
        }
    }

    public void tick(Level level, BlockPos pos, BlockState state) {
        if (!level.isClientSide) {
            transferEnergyToNeighbors(level, pos);
            setChanged();
            level.sendBlockUpdated(pos, state, state, 3);
        }
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.put("Inventory", inventory.serializeNBT());
        tag.putInt("EnergyStored", energyStorage.getEnergyStored());
        tag.putInt("BurnTime", burnTime);
        tag.putInt("TotalBurnTime", totalBurnTime);
        tag.putInt("CrushProgress", progress);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        inventory.deserializeNBT(tag.getCompound("Inventory"));
        int savedEnergy = tag.getInt("EnergyStored");
        try {
            Field energyField = EnergyStorage.class.getDeclaredField("energy");
            energyField.setAccessible(true);
            energyField.setInt(energyStorage, Math.min(savedEnergy, energyStorage.getMaxEnergyStored()));
        } catch (NoSuchFieldException | IllegalAccessException e) {
            while (energyStorage.getEnergyStored() > 0) {
                energyStorage.extractEnergy(energyStorage.getMaxEnergyStored(), false);
            }
            energyStorage.receiveEnergy(savedEnergy, false);
        }
        burnTime = tag.getInt("BurnTime");
        totalBurnTime = tag.getInt("TotalBurnTime");
        progress = tag.getInt("CrushProgress");
    }

    @Override
    public ClientboundBlockEntityDataPacket getUpdatePacket() {
        return ClientboundBlockEntityDataPacket.create(this);
    }

    @Override
    public void onDataPacket(Connection net, ClientboundBlockEntityDataPacket pkt) {
        handleUpdateTag(pkt.getTag());
    }

    @Override
    public CompoundTag getUpdateTag() {
        return saveWithoutMetadata();
    }

    @Override
    public void handleUpdateTag(CompoundTag tag) {
        load(tag);
    }

    @Override
    public @NotNull <T> LazyOptional<T> getCapability(@NotNull Capability<T> cap, @Nullable Direction side) {
        if (cap == ENERGY) {
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyHandler.invalidate();
    }

    protected int internalExtractEnergy(int maxExtract, boolean simulate) {
        int energyExtracted = Math.min(energyStorage.getEnergyStored(), Math.min(100, maxExtract));
        if (!simulate) {
            try {
                Field energyField = EnergyStorage.class.getDeclaredField("energy");
                energyField.setAccessible(true);
                energyField.setInt(energyStorage, energyStorage.getEnergyStored() - energyExtracted);
            } catch (NoSuchFieldException | IllegalAccessException e) {
                energyStorage.extractEnergy(energyExtracted, false);
            }
        }
        return energyExtracted;
    }
}