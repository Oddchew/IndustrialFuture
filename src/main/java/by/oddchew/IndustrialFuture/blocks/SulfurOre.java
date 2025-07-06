package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.custom.SulfurOreBlock;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class SulfurOre {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialFuture.MODID);

    public static final RegistryObject<Block> SULFUR_ORE =
            BLOCKS.register("sulfur_ore", () -> new SulfurOreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F) // Твёрдость и сопротивление как у железной руды
                    //.requiresCorrectToolForDrops() // Требуется правильный инструмент
                    .sound(SoundType.STONE)
            ));

    public static final RegistryObject<Block> DEEPSLATE_SULFUR_ORE =
            BLOCKS.register("deepslate_sulfur_ore", () -> new SulfurOreBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(4.5F, 6.0F) // Твёрже и устойчивее
                    //.requiresCorrectToolForDrops()
                    .sound(SoundType.DEEPSLATE)));
}