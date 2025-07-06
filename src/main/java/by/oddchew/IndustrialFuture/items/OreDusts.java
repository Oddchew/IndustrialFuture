package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class OreDusts {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> IRON_DUST =
            ITEMS.register("iron_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> TIN_DUST =
            ITEMS.register("tin_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> COPPER_DUST =
            ITEMS.register("copper_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> GOLD_DUST =
            ITEMS.register("gold_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> COAL_DUST =
            ITEMS.register("coal_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> STONE_DUST =
            ITEMS.register("stone_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> LAPIS_DUST =
            ITEMS.register("lapis_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> DIAMOND_DUST =
            ITEMS.register("diamond_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));

    public static final RegistryObject<Item> SULFUR_DUST =
            ITEMS.register("sulfur_dust", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            .stacksTo(64)
            ));
}
