package by.oddchew.IndustrialFuture.blockentity.logic;

import by.oddchew.IndustrialFuture.items.BlockItems;
import by.oddchew.IndustrialFuture.items.Tin;
import by.oddchew.IndustrialFuture.slot.SlotCrushed;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraftforge.items.ItemStackHandler;

import static by.oddchew.IndustrialFuture.items.OreDusts.*;

public class CrusherLogic {
    public void CreatingDust(ItemStackHandler inventory) {
        ItemStack inputStack = inventory.getStackInSlot(0);
        Item dust = SolidToDust(inputStack);
        if (dust != null && ElementMatch(inputStack, inventory.getStackInSlot(2))) {
            int quantity = 1;
            if(SlotCrushed.isOre(inputStack.getItem())){
                quantity = 2;
            }
            if(SlotCrushed.isDustuOre(inputStack.getItem())) {
                quantity = 4;
            }
            ItemStack itemToAdd = new ItemStack(dust, quantity);
            inventory.insertItem(2, itemToAdd, false); // Добавляем 1 пыль
        }
    }

    public boolean ElementMatch(ItemStack itemSolid, ItemStack itemDust){
        if (itemDust.isEmpty()) {
            return true; // Выходной слот пустой, можно дробить
        }
        Item dust =  SolidToDust(itemSolid);
        return dust != null && dust == itemDust.getItem();
    }

    private Item SolidToDust(ItemStack solid){
        Item item = solid.getItem();
        //System.out.println(item.getName(solid).toString());
        if(item == Items.STONE || item == Items.COBBLESTONE){
            return STONE_DUST.get();
        }

        if(item == Items.COPPER_ORE || item == Items.RAW_COPPER || item == Items.DEEPSLATE_COPPER_ORE || item == Items.COPPER_INGOT){
            return COPPER_DUST.get();
        }

        if(item == Items.IRON_ORE || item == Items.RAW_IRON || item == Items.DEEPSLATE_IRON_ORE || item == Items.IRON_INGOT){
            return IRON_DUST.get();
        }

        if(item == Items.GOLD_ORE || item == Items.RAW_GOLD || item == Items.DEEPSLATE_GOLD_ORE || item == Items.GOLD_INGOT){
            return GOLD_DUST.get();
        }

        if(item == Items.DIAMOND_ORE || item == Items.DIAMOND || item == Items.DEEPSLATE_DIAMOND_ORE){
            return DIAMOND_DUST.get();
        }

        if(item == Items.COAL_ORE || item == Items.COAL || item == Items.DEEPSLATE_COAL_ORE){
            return COAL_DUST.get();
        }

        if(item == Items.LAPIS_ORE || item == Items.LAPIS_LAZULI || item == Items.DEEPSLATE_LAPIS_ORE){
            return LAPIS_DUST.get();
        }

        if(item == BlockItems.TIN_ORE.get() || item == Tin.RAW_TIN.get() || item == BlockItems.DEEPSLATE_TIN_ORE.get() || item == Tin.TIN_INGOT.get()){
            return TIN_DUST.get();
        }

        if(item == Items.REDSTONE_ORE || item == Items.DEEPSLATE_REDSTONE_ORE){
            return Items.REDSTONE;
        }

        if(item == BlockItems.SULFUR_ORE.get() || item == BlockItems.DEEPLATE_SULFUR_ORE.get()){
            return SULFUR_DUST.get();
        }
        return null;
    }
}
