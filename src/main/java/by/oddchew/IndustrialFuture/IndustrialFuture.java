package by.oddchew.IndustrialFuture;

import by.oddchew.IndustrialFuture.blockentity.ModBlockEntities;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.blocks.SulfurOre;
import by.oddchew.IndustrialFuture.blocks.TinOre;
import by.oddchew.IndustrialFuture.event.ClientEventHandler;
import by.oddchew.IndustrialFuture.fluid.ModFluids;
import by.oddchew.IndustrialFuture.items.*;
import by.oddchew.IndustrialFuture.menu.ModMenuTypes;
import by.oddchew.IndustrialFuture.worldgen.ModConfiguredFeatures;
import by.oddchew.IndustrialFuture.worldgen.ModPlacedFeatures;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.common.world.BiomeModifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@Mod(IndustrialFuture.MODID)
public class IndustrialFuture {
    public static final String MODID = "industrial_future";
    public static final Logger LOGGER = LogManager.getLogger(MODID);

    public IndustrialFuture() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        modEventBus.addListener(ClientEventHandler::onClientLiquids);
        modEventBus.addListener(ClientEventHandler::onClientScreen);
        modEventBus.addListener(ClientEventHandler::onClientSetup);

        LuminerisRegistry.ITEMS.register(modEventBus);
        PetroleumBucketRegistry.ITEMS.register(modEventBus);
        ModFluids.FLUIDS.register(modEventBus);
        OilBucketRegistry.ITEMS.register(modEventBus);
        ModFluids.BLOCKS.register(modEventBus);
        ModFluids.FLUID_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        BlockItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        OreDusts.ITEMS.register(modEventBus);
        Tin.ITEMS.register(modEventBus);
        Rubber.ITEMS.register(modEventBus);
        SulfurOre.BLOCKS.register(modEventBus);
        TinOre.BLOCKS.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
    }
}