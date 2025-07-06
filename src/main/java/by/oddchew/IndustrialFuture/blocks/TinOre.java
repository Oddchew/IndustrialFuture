package by.oddchew.IndustrialFuture.blocks;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class TinOre {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialFuture.MODID);

    public static final RegistryObject<Block> TIN_ORE =
            BLOCKS.register("tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));

    public static final RegistryObject<Block> DEEPSLATE_TIN_ORE =
            BLOCKS.register("deepslate_tin_ore", () -> new Block(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(3.0F, 3.0F)
                    .requiresCorrectToolForDrops()
                    .sound(SoundType.STONE)
            ));
}
