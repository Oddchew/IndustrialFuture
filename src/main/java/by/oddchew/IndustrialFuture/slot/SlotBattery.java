package by.oddchew.IndustrialFuture.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY;

public class SlotBattery extends SlotItemHandler {
    public SlotBattery(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        return stack.getCapability(ENERGY).isPresent();
    }
}