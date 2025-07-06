package by.oddchew.IndustrialFuture.blocks.custom;

import by.oddchew.IndustrialFuture.items.OreDusts;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.storage.loot.LootContext;
import java.util.Collections;
import java.util.List;

public class SulfurOreBlock extends Block {
    public SulfurOreBlock(Properties properties) {
        super(properties);
    }

    @Override
    public List<ItemStack> getDrops(BlockState state, LootContext.Builder builder) {
        return Collections.emptyList();
    }
}