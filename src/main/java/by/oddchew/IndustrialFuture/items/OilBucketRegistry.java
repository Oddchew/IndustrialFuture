package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.fluid.custom.LiquidFuel;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static by.oddchew.IndustrialFuture.fluid.ModFluids.OIL_SOURCE;

public class OilBucketRegistry {
    public static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> OIL_BUCKET = ITEMS.register("oil_bucket",
            () -> new BucketItem(OIL_SOURCE, new Item.Properties()
                    .craftRemainder(Items.BUCKET).stacksTo(1))
    );
}