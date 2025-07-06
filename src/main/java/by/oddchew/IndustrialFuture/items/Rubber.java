package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Rubber {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> RAW_RUBBER =
            ITEMS.register("raw_rubber", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> RUBBER =
            ITEMS.register("rubber", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));
}
