package by.oddchew.IndustrialFuture.items;


import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import by.oddchew.IndustrialFuture.fluid.custom.LiquidFuel;
import net.minecraft.world.food.FoodProperties;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static by.oddchew.IndustrialFuture.fluid.ModFluids.OIL_SOURCE;
import static by.oddchew.IndustrialFuture.fluid.ModFluids.PETROLEUM_SOURCE;

public class ModItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> BATTERY = ITEMS.register("battery",
            () -> new Battery(new Item.Properties().tab(ModCreativeModeTab.INDUSTRIAL_TAB),0));

    public static final RegistryObject<Item> DANDELION_SEEDS = ITEMS.register("dandelion_seeds",
            () -> new DandelionSeeds(new Item.Properties()
                    .tab(ModCreativeModeTab.INDUSTRIAL_TAB)));

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

    public static final RegistryObject<Item> PETROLEUM_BUCKET = ITEMS.register("petroleum_bucket",
            () -> new LiquidFuel(PETROLEUM_SOURCE, new Item.Properties()
                    .craftRemainder(Items.BUCKET).stacksTo(1))
    );

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(OIL_SOURCE, new Item.Properties()
                    .craftRemainder(Items.BUCKET).stacksTo(1))
    );

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

    public static final RegistryObject<Item> TIN_INGOT =
            ITEMS.register("tin_ingot", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
            ));

    public static final RegistryObject<Item> RAW_TIN =
            ITEMS.register("raw_tin", () -> new Item(
                    new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
            ));

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

    public static void register(IEventBus eventBus) {
        ITEMS.register(eventBus);
    }
}
