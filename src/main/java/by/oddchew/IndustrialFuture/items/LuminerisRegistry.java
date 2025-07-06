package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class LuminerisRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> LUMINERIS =
            ITEMS.register("lumineris", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
                            .food(new FoodProperties.Builder()
                                    .nutrition(4)
                                    .saturationMod(0.6f)
                                    .build()
                            )
            ));
}
