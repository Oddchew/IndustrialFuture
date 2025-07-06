package by.oddchew.IndustrialFuture.slot;

import net.minecraft.world.Container;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.RecipeType;
import net.minecraft.world.level.Level;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;

public class SlotFusible extends SlotItemHandler {
    private final Level level;
    public SlotFusible(ItemStackHandler container, int index, int x, int y, Level level ) {
        super(container, index, x, y);
        this.level = level;
    }

    @Override
    public boolean mayPlace(ItemStack stack) {
        if (stack.isEmpty()) {
            return false;
        }
        // Проверяем, есть ли рецепт плавки для этого предмета
        return level.getRecipeManager().getRecipeFor(RecipeType.SMELTING, new InventoryWrapper(stack), level)
                .map(recipe -> !recipe.getResultItem().isEmpty())
                .orElse(false);
    }

    private static class InventoryWrapper implements Container {
        private final ItemStack stack;

        public InventoryWrapper(ItemStack stack) {
            this.stack = stack;
        }

        @Override
        public int getContainerSize() {
            return 1;
        }

        @Override
        public boolean isEmpty() {
            return stack.isEmpty();
        }

        @Override
        public ItemStack getItem(int index) {
            return index == 0 ? stack : ItemStack.EMPTY;
        }

        @Override
        public ItemStack removeItem(int index, int count) {
            return ItemStack.EMPTY; // Не поддерживаем извлечение
        }

        @Override
        public ItemStack removeItemNoUpdate(int index) {
            return ItemStack.EMPTY; // Не поддерживаем извлечение
        }

        @Override
        public void setItem(int index, ItemStack stack) {
            // Ничего не делаем
        }

        @Override
        public void setChanged() {
            // Ничего не делаем
        }

        @Override
        public boolean stillValid(net.minecraft.world.entity.player.Player player) {
            return true;
        }

        @Override
        public void clearContent() {
            // Ничего не делаем
        }
    }
}
