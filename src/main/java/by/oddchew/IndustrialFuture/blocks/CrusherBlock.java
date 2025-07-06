package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.blockentity.CrusherBlockEntity;
import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;

public class CrusherBlock extends AbstractMachineBlock {
    public CrusherBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new CrusherBlockEntity(pos, state);
    }
}