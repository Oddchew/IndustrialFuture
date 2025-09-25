package by.oddchew.IndustrialFuture.event;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.items.ModItems;
import net.minecraft.world.item.CreativeModeTab;
import net.minecraft.world.item.ItemStack;
import net.minecraft.core.NonNullList;

public class ModCreativeModeTab {
    public static final CreativeModeTab INDUSTRIAL_TAB = new CreativeModeTab(IndustrialFuture.MODID) {
        @Override
        public ItemStack makeIcon() {
            return new ItemStack(ModItems.LUMINERIS.get());
        }

        @Override
        public void fillItemList(NonNullList<ItemStack> items) {
            super.fillItemList(items);

            // Добавляем батарею с 10000 FE
            ItemStack batteryFull = new ItemStack(ModItems.BATTERY.get());
            batteryFull.getOrCreateTag().putInt("Energy", 10000);
            items.add(batteryFull);

            items.add(new ItemStack(ModItems.OIL_BUCKET.get()));
            items.add(new ItemStack(ModItems.PETROLEUM_BUCKET.get()));

        } // Добавление предметов в вкладку
    };
}
