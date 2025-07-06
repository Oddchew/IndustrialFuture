package by.oddchew.IndustrialFuture.event;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.items.LuminerisRegistry;
import by.oddchew.IndustrialFuture.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

import static by.oddchew.IndustrialFuture.items.OilBucketRegistry.OIL_BUCKET;
import static by.oddchew.IndustrialFuture.items.PetroleumBucketRegistry.PETROLEUM_BUCKET;

public class ModCreativeModeTab {
    public static final CreativeModeTab INDUSTRIAL_TAB = new CreativeModeTab(IndustrialFuture.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(LuminerisRegistry.LUMINERIS.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);

            // Добавляем батарею с 10000 FE
            ItemStack batteryFull = new ItemStack(ModItems.BATTERY.get());
            batteryFull.getOrCreateTag().putInt("Energy", 10000);
            items.add(batteryFull);

            items.add(new ItemStack(OIL_BUCKET.get()));
            items.add(new ItemStack(PETROLEUM_BUCKET.get()));

        } // Добавление предметов в вкладку
    };
}
