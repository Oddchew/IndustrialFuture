package by.oddchew.IndustrialFuture.slot;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import net.minecraft.world.item.BucketItem;
import net.minecraft.world.item.ItemStack;
import net.minecraftforge.common.ForgeHooks;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotBurn extends SlotItemHandler {
    public SlotBurn(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        // Проверяем, является ли предмет ведром с жидкостью
        if (stack.getItem() instanceof BucketItem) {
            IndustrialFuture.LOGGER.debug("Rejected bucket item in SlotBurn: {}", stack);
            return false;
        }
        // Проверяем, является ли предмет топливом
        boolean isFuel = ForgeHooks.getBurnTime(stack, null) > 0;
        if (!isFuel) {
            IndustrialFuture.LOGGER.debug("Item is not fuel for SlotBurn: {}", stack);
        }
        return isFuel;
    }
}
