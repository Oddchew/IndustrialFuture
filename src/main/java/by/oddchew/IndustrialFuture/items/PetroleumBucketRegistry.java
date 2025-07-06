package by.oddchew.IndustrialFuture.items;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.fluid.custom.LiquidFuel;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.Items;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

import static by.oddchew.IndustrialFuture.fluid.ModFluids.PETROLEUM_SOURCE;

public class PetroleumBucketRegistry {
    public static final DeferredRegister<Item> ITEMS =
            DeferredRegister.create(ForgeRegistries.ITEMS, IndustrialFuture.MODID);

    public static final RegistryObject<Item> PETROLEUM_BUCKET = ITEMS.register("petroleum_bucket",
            () -> new LiquidFuel(PETROLEUM_SOURCE, new Item.Properties()
                    .craftRemainder(Items.BUCKET).stacksTo(1))
    );
}
