package by.oddchew.IndustrialFuture.blockentity;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.CableBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.common.capabilities.Capability;
import net.minecraftforge.common.capabilities.ForgeCapabilities;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.energy.IEnergyStorage;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public class CableBlockEntity extends BlockEntity {
    private int energyPerReceiver;

    private final LazyOptional<IEnergyStorage> energyHandler = LazyOptional.of(() -> new IEnergyStorage() {
        @Override
        public int receiveEnergy(int maxReceive, boolean simulate) {
            return 0; // Кабель не хранит энергию
        }

        @Override
        public int extractEnergy(int maxExtract, boolean simulate) {
            return 0; // Кабель не хранит энергию
        }

        @Override
        public int getEnergyStored() {
            return 0;
        }

        @Override
        public int getMaxEnergyStored() {
            return 0;
        }

        @Override
        public boolean canExtract() {
            return false;
        }

        @Override
        public boolean canReceive() {
            return false;
        }
    });

    public CableBlockEntity(BlockPos pos, BlockState state, int maxEnergyPerReceiver) {
        super(ModBlockEntities.COPPER_CABLE.get(), pos, state);
        this.energyPerReceiver = maxEnergyPerReceiver;
        IndustrialFuture.LOGGER.debug("CableBlockEntity created at {} with energyPerReceiver={}", pos, maxEnergyPerReceiver);
    }

    // Фабричный метод для совместимости с BlockEntityType.Builder.of
    public static CableBlockEntity create(BlockPos pos, BlockState state) {
        // Здесь мы не можем напрямую получить maxEnergyPerReceiver, поэтому полагаемся на CableBlock
        // Значение будет установлено через newBlockEntity в CableBlock
        return new CableBlockEntity(pos, state, 0); // Значение по умолчанию, будет перезаписано
    }

    @Override
    protected void saveAdditional(CompoundTag tag) {
        super.saveAdditional(tag);
        tag.putInt("EnergyPerReceiver", energyPerReceiver);
        IndustrialFuture.LOGGER.debug("Saving CableBlockEntity at {}: EnergyPerReceiver={}", getBlockPos(), energyPerReceiver);
    }

    @Override
    public void load(CompoundTag tag) {
        super.load(tag);
        if (tag.contains("EnergyPerReceiver")) {
            energyPerReceiver = tag.getInt("EnergyPerReceiver");
            IndustrialFuture.LOGGER.debug("Loaded CableBlockEntity at {}: EnergyPerReceiver={}", getBlockPos(), energyPerReceiver);
        }
    }

    public void tick() {
        if (level == null || level.isClientSide) return;

        Network network = findNetwork(level, worldPosition);
        List<EnergyDevice> sources = network.sources;
        List<EnergyDevice> receivers = network.receivers;

        if (!sources.isEmpty() && !receivers.isEmpty()) {
            int totalEnergyAvailable = 0;

            for (EnergyDevice source : sources) {
                totalEnergyAvailable += source.storage.extractEnergy(Integer.MAX_VALUE, true);
            }

            int totalEnergyToTransfer = Math.min(totalEnergyAvailable, energyPerReceiver * receivers.size());

            if (totalEnergyToTransfer > 0) {
                int energyPerReceiverActual = Math.min(energyPerReceiver, totalEnergyToTransfer / receivers.size());
                for (EnergyDevice receiver : receivers) {
                    int energyReceived = receiver.storage.receiveEnergy(energyPerReceiverActual, false);
                    if (energyReceived > 0) {
                        int energyToExtract = energyReceived;
                        for (EnergyDevice source : sources) {
                            int extractable = source.storage.extractEnergy(energyToExtract, true);
                            if (extractable > 0) {
                                int extracted = source.storage.extractEnergy(extractable, false);
                                energyToExtract -= extracted;
                                if (energyToExtract <= 0) break;
                            }
                        }
                    }
                }
            }
        }
    }

    private static class EnergyDevice {
        BlockPos pos;
        Direction direction;
        IEnergyStorage storage;

        EnergyDevice(BlockPos pos, Direction direction, IEnergyStorage storage) {
            this.pos = pos;
            this.direction = direction;
            this.storage = storage;
        }
    }

    private static class Network {
        Set<BlockPos> cables;
        List<EnergyDevice> sources;
        List<EnergyDevice> receivers;

        Network() {
            cables = new HashSet<>();
            sources = new ArrayList<>();
            receivers = new ArrayList<>();
        }
    }

    private Network findNetwork(Level level, BlockPos startPos) {
        Network network = new Network();
        Set<BlockPos> visited = new HashSet<>();
        Queue<BlockPos> queue = new LinkedList<>();

        queue.add(startPos);
        visited.add(startPos);
        network.cables.add(startPos);

        while (!queue.isEmpty()) {
            BlockPos pos = queue.poll();
            for (Direction direction : Direction.values()) {
                BlockPos neighborPos = pos.relative(direction);
                if (!visited.contains(neighborPos)) {
                    BlockState neighborState = level.getBlockState(neighborPos);
                    BlockEntity neighborEntity = level.getBlockEntity(neighborPos);

                    if (neighborState.getBlock() instanceof CableBlock) {
                        queue.add(neighborPos);
                        visited.add(neighborPos);
                        network.cables.add(neighborPos);
                    } else if (neighborEntity != null) {
                        LazyOptional<IEnergyStorage> capability = neighborEntity.getCapability(ForgeCapabilities.ENERGY, direction.getOpposite());
                        capability.ifPresent(storage -> {
                            if (storage.canExtract()) {
                                network.sources.add(new EnergyDevice(neighborPos, direction, storage));
                            }
                            if (storage.canReceive()) {
                                network.receivers.add(new EnergyDevice(neighborPos, direction, storage));
                            }
                        });
                    }
                }
            }
        }
        return network;
    }

    @Nonnull
    @Override
    public <T> LazyOptional<T> getCapability(@Nonnull Capability<T> cap, @Nullable Direction side) {
        if (cap == ForgeCapabilities.ENERGY) {
            return energyHandler.cast();
        }
        return super.getCapability(cap, side);
    }

    @Override
    public void invalidateCaps() {
        super.invalidateCaps();
        energyHandler.invalidate();
    }
}