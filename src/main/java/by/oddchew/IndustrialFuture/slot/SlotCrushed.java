package by.oddchew.IndustrialFuture.slot;

import by.oddchew.IndustrialFuture.items.BlockItems;
import by.oddchew.IndustrialFuture.items.Tin;
import net.minecraft.world.item.BlockItem;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;
import net.minecraftforge.items.SlotItemHandler;


public class SlotCrushed extends SlotItemHandler {
    public SlotCrushed(ItemStackHandler container, int index, int x, int y) {
        super(container, index, x, y);
    }

    public static boolean isOre(Item item){
        boolean isIronOre = item == Items.IRON_ORE || item == Items.DEEPSLATE_IRON_ORE || item == Items.RAW_IRON;
        boolean isCopperOre = item == Items.COPPER_ORE || item == Items.DEEPSLATE_COPPER_ORE || item == Items.RAW_COPPER;
        boolean isGoldOre = item == Items.GOLD_ORE || item == Items.DEEPSLATE_GOLD_ORE || item == Items.RAW_GOLD;
        boolean isLapisOre = item == Items.LAPIS_ORE || item == Items.DEEPSLATE_LAPIS_ORE;
        boolean isTinOre =  item == BlockItems.TIN_ORE.get() || item == BlockItems.DEEPSLATE_TIN_ORE.get() || item == Tin.RAW_TIN.get();
        return isCopperOre || isGoldOre || isIronOre || isLapisOre || isTinOre;
    }

    public static boolean isDustuOre(Item item){
        boolean isRedstoneOre = item == Items.REDSTONE_ORE || item == Items.DEEPSLATE_REDSTONE_ORE;
        boolean isSulfurOre = item == BlockItems.SULFUR_ORE.get() || item == BlockItems.DEEPLATE_SULFUR_ORE.get();
        return isRedstoneOre || isSulfurOre;
    }

    public static boolean isIngot(Item item){
        boolean isIron = item == Items.IRON_INGOT;
        boolean isCopper = item == Items.COPPER_INGOT;
        boolean isGold = item == Items.GOLD_INGOT;
        boolean isTin = item == Tin.TIN_INGOT.get();
        return isCopper || isGold || isIron || isTin;
    }
    @Override
    public boolean mayPlace(ItemStack stack) {
        Item item = stack.getItem();

        boolean isOre = isOre(item);
        boolean isIngot = isIngot(item);
        boolean isDustyOre = isDustuOre(item);
        // Проверка на конкретные предметы
        boolean isDiamond = item == Items.DIAMOND;
        boolean isLapis = item == Items.LAPIS_LAZULI;
        boolean isCoal = item == Items.COAL;
        boolean isStone = item == Items.STONE;
        boolean isCobblestone = item == Items.COBBLESTONE;

        // Возвращаем true, если предмет соответствует любому из условий
        return isOre || isIngot || isDustyOre || isDiamond || isCoal || isStone || isCobblestone || isLapis;
    }
}
