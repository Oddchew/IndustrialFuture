package by.oddchew.IndustrialFuture;

import by.oddchew.IndustrialFuture.blockentity.ModBlockEntities;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.event.ClientEventHandler;
import by.oddchew.IndustrialFuture.fluid.ModFluids;
import by.oddchew.IndustrialFuture.items.BlockItems;
import by.oddchew.IndustrialFuture.items.ModItems;
import by.oddchew.IndustrialFuture.menu.ModMenuTypes;
import by.oddchew.IndustrialFuture.worldgen.ModConfiguredFeatures;
import by.oddchew.IndustrialFuture.worldgen.ModPlacedFeatures;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
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

        ModFluids.FLUIDS.register(modEventBus);
        ModFluids.BLOCKS.register(modEventBus);
        ModFluids.FLUID_TYPES.register(modEventBus);
        ModBlocks.BLOCKS.register(modEventBus);
        BlockItems.ITEMS.register(modEventBus);
        ModBlockEntities.BLOCK_ENTITIES.register(modEventBus);
        ModMenuTypes.MENU_TYPES.register(modEventBus);
        ModItems.ITEMS.register(modEventBus);
        ModPlacedFeatures.PLACED_FEATURES.register(modEventBus);
        ModConfiguredFeatures.CONFIGURED_FEATURES.register(modEventBus);
    }
}