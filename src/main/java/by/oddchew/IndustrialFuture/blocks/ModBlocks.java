package by.oddchew.IndustrialFuture.blocks;


import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blockentity.ElectricFurnaceEntity;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.Material;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlocks {
    public static final DeferredRegister<Block> BLOCKS =
            DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialFuture.MODID);

    public static final RegistryObject<CrusherBlock> CRUSHER = BLOCKS.register("crusher",
            () -> new CrusherBlock(BlockBehaviour.Properties.of(Material.STONE)
                    .strength(1.5F)
                    .sound(SoundType.STONE)));

    public static final RegistryObject<MachineBlock> MACHINE = BLOCKS.register("machine",
            () -> new MachineBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5F)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<ElectricFurnaceBlock> ELECRTIC_FURNACE = BLOCKS.register("electric_furnace",
            () -> new ElectricFurnaceBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(1.5F)
                    .sound(SoundType.METAL)));

    public static final RegistryObject<CableBlock> COPPER_CABLE = BLOCKS.register("copper_cable",
            () -> new CableBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(0.5F)
                    .sound(SoundType.METAL)
                    .noOcclusion(), 512
            ));

    public static final RegistryObject<CableBlock> COPPER_CABLE_200 = BLOCKS.register("copper_cable_200",
            () -> new CableBlock(BlockBehaviour.Properties.of(Material.METAL)
                    .strength(0.5F)
                    .sound(SoundType.METAL)
                    .noOcclusion(), 200));

    public static final RegistryObject<DandelionCropBlock> DANDELION_CROP = BLOCKS.register("dandelion_crop",
            () -> new DandelionCropBlock(BlockBehaviour.Properties.of(Material.PLANT)
                    .noCollission()
                    .randomTicks()
                    .instabreak()
                    .sound(SoundType.CROP)));

    public static void register(IEventBus eventBus) {
        BLOCKS.register(eventBus);
    }
}