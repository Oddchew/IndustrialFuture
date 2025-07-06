package by.oddchew.IndustrialFuture.items;


import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> BATTERY = ITEMS.register("battery",
            () -> new by.oddchew.IndustrialFuture.items.Battery(new Item.Properties().tab(ModCreativeModeTab.INDUSTRIAL_TAB),0));

    public static final RegistryObject<Item> DANDELION_SEEDS = ITEMS.register("dandelion_seeds",
            () -> new DandelionSeeds(new Item.Properties()
                    .tab(ModCreativeModeTab.INDUSTRIAL_TAB)));

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
