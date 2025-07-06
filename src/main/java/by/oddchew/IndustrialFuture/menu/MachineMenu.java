package by.oddchew.IndustrialFuture.menu;

import by.oddchew.IndustrialFuture.blockentity.MachineBlockEntity;
import by.oddchew.IndustrialFuture.blockentity.custom.AbstractMachineBlockEntity;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.menu.custom.AbstractMachineMenu;
import by.oddchew.IndustrialFuture.slot.SlotBattery;
import by.oddchew.IndustrialFuture.slot.SlotBurn;
import by.oddchew.IndustrialFuture.slot.SlotCrushed;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraftforge.items.SlotItemHandler;

public class MachineMenu extends AbstractMachineMenu {
    protected MachineBlockEntity blockEntity;

    public MachineMenu(int id, Inventory playerInv, MachineBlockEntity blockEntity) {
        super(ModMenuTypes.MACHINE_MENU.get(), id, playerInv, blockEntity, ModBlocks.MACHINE.get());
        this.blockEntity = blockEntity;
        addInventorySlots(blockEntity.getInventory().getSlots(), 56, 17, 56, 53);

    }

    @Override
    public MachineBlockEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    protected Slot createInputSlot(int index, int x, int y) {
        return new SlotBurn(blockEntity.getInventory(), index, x, y);
    }

}