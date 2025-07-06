package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.blockentity.MachineBlockEntity;
import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class MachineBlock extends AbstractMachineBlock {
    public MachineBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new MachineBlockEntity(pos, state);
    }
}