package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.blocks.SulfurOre;
import by.oddchew.IndustrialFuture.blocks.TinOre;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.item.BlockItem;

public class BlockItems {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> MACHINE_ITEM =
            ITEMS.register("machine", () ->
                    new BlockItem(ModBlocks.MACHINE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            ));
    public static final RegistryObject<Item> CRUSHER_ITEM =
            ITEMS.register("crusher", () ->
                    new BlockItem(ModBlocks.CRUSHER.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                            ));

    public static final RegistryObject<Item> ELECTRIC_FURNACE_ITEM =
            ITEMS.register("electric_furnace", () ->
                    new BlockItem(ModBlocks.ELECRTIC_FURNACE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));

    public static final RegistryObject<Item> COPPER_CABLE_ITEM =
            ITEMS.register("copper_cable", () ->
                    new BlockItem(ModBlocks.COPPER_CABLE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));

    public static final RegistryObject<Item> SULFUR_ORE =
            ITEMS.register("sulfur_ore", () ->
                    new BlockItem(SulfurOre.SULFUR_ORE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));

    public static final RegistryObject<Item> DEEPLATE_SULFUR_ORE =
            ITEMS.register("deepslate_sulfur_ore", () ->
                    new BlockItem(SulfurOre.DEEPSLATE_SULFUR_ORE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));

    public static final RegistryObject<Item> TIN_ORE =
            ITEMS.register("tin_ore", () ->
                    new BlockItem(TinOre.TIN_ORE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));

    public static final RegistryObject<Item> DEEPSLATE_TIN_ORE =
            ITEMS.register("deepslate_tin_ore", () ->
                    new BlockItem(TinOre.DEEPSLATE_TIN_ORE.get(), new Item.Properties()
                            .tab(ModCreativeModeTab.INDUSTRIAL_TAB)
                    ));
}

