package by.oddchew.IndustrialFuture.menu;

import by.oddchew.IndustrialFuture.blockentity.CrusherBlockEntity;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.menu.custom.AbstractMachineMenu;
import by.oddchew.IndustrialFuture.slot.SlotBattery;
import by.oddchew.IndustrialFuture.slot.SlotBurn;
import by.oddchew.IndustrialFuture.slot.SlotCrushed;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

public class CrusherMenu extends AbstractMachineMenu {
    protected CrusherBlockEntity blockEntity;

    public CrusherMenu(int id, Inventory playerInv, CrusherBlockEntity blockEntity) {
        super(ModMenuTypes.CRUSHER_MENU.get(), id, playerInv, blockEntity, ModBlocks.CRUSHER.get());
        this.blockEntity = blockEntity;
        addInventorySlots(blockEntity.getInventory().getSlots(), 56, 53, 56, 17, 92, 35);
        System.out.println("Inventory size: " + blockEntity.getInventory().getSlots());
    }

    @Override
    public CrusherBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    protected Slot createInputSlot(int index, int x, int y) {
        return new SlotCrushed(blockEntity.getInventory(), index, x, y);
    }

}