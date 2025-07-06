package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.event.ModCreativeModeTab;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class Tin {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);
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
}
