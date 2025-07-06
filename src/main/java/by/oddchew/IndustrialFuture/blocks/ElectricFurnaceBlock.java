package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.blockentity.ElectricFurnaceEntity;
import by.oddchew.IndustrialFuture.blocks.custom.AbstractMachineBlock;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntity;
import net.minecraft.world.level.block.state.BlockState;

public class ElectricFurnaceBlock extends AbstractMachineBlock {
    public ElectricFurnaceBlock(Properties properties) {
        super(properties);
    }

    @Override
    public BlockEntity newBlockEntity(BlockPos pos, BlockState state) {
        return new ElectricFurnaceEntity(pos, state);
    }
}
