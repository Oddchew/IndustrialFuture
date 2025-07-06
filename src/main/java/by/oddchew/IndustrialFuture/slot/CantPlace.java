package by.oddchew.IndustrialFuture.slot;

import net.minecraft.world.item.ItemStack;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;
import org.jetbrains.annotations.NotNull;

import static net.minecraftforge.common.capabilities.ForgeCapabilities.ENERGY;

public class CantPlace extends SlotItemHandler {
    public CantPlace(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
    }

    @Override
    public boolean mayPlace(@NotNull ItemStack stack) {
        return false;
    }
}
