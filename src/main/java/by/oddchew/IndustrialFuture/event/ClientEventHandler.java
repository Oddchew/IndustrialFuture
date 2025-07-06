package by.oddchew.IndustrialFuture.event;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.blocks.SulfurOre;
import by.oddchew.IndustrialFuture.fluid.ModFluids;
import by.oddchew.IndustrialFuture.menu.ModMenuTypes;
import by.oddchew.IndustrialFuture.menu.screen.CrusherScreen;
import by.oddchew.IndustrialFuture.menu.screen.MachineScreen;
import by.oddchew.IndustrialFuture.menu.screen.ElectricFurnaceScreen;
import net.minecraft.client.gui.screens.MenuScreens;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.ItemBlockRenderTypes;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

@Mod.EventBusSubscriber(modid = IndustrialFuture.MODID, bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class ClientEventHandler {
    @SubscribeEvent
    public static void onClientSetup(FMLClientSetupEvent event) {
        ItemBlockRenderTypes.setRenderLayer(ModBlocks.DANDELION_CROP.get(), RenderType.cutout());
        ItemBlockRenderTypes.setRenderLayer(SulfurOre.SULFUR_ORE.get(), RenderType.solid());
        ItemBlockRenderTypes.setRenderLayer(SulfurOre.DEEPSLATE_SULFUR_ORE.get(), RenderType.solid());
    }

    public static void onClientLiquids(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_SOURCE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.OIL_FLOWING.get(), RenderType.translucent());

            ItemBlockRenderTypes.setRenderLayer(ModFluids.PETROLEUM_BLOCK.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.PETROLEUM_SOURCE.get(), RenderType.translucent());
            ItemBlockRenderTypes.setRenderLayer(ModFluids.PETROLEUM_FLOWING.get(), RenderType.translucent());
        });
    }

    public static void onClientScreen(FMLClientSetupEvent event) {
        event.enqueueWork(() -> {
            MenuScreens.register(ModMenuTypes.MACHINE_MENU.get(), MachineScreen::new);
            MenuScreens.register(ModMenuTypes.CRUSHER_MENU.get(), CrusherScreen::new);
            MenuScreens.register(ModMenuTypes.ELECTRIC_FURNACE_MENU.get(), ElectricFurnaceScreen::new);
        });
    }
}