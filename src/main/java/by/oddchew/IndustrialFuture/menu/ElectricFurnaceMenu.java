package by.oddchew.IndustrialFuture.menu;

import by.oddchew.IndustrialFuture.blockentity.ElectricFurnaceEntity;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import by.oddchew.IndustrialFuture.menu.custom.AbstractMachineMenu;

import by.oddchew.IndustrialFuture.slot.SlotFusible;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.level.Level;

public class ElectricFurnaceMenu extends AbstractMachineMenu {
    protected ElectricFurnaceEntity blockEntity;
    private final Level level;

    public ElectricFurnaceMenu(int id, Inventory playerInv, ElectricFurnaceEntity blockEntity, Level level) {
        super(ModMenuTypes.ELECTRIC_FURNACE_MENU.get(), id, playerInv, blockEntity, ModBlocks.ELECRTIC_FURNACE.get());
        this.blockEntity = blockEntity;
        this.level = level;
        addInventorySlots(blockEntity.getInventory().getSlots(), 56, 53, 56, 17, 92, 35);
        System.out.println("Inventory size: " + blockEntity.getInventory().getSlots());
    }

    @Override
    public ElectricFurnaceEntity getBlockEntity() {
        return this.blockEntity;
    }

    @Override
    protected Slot createInputSlot(int index, int x, int y) {
        return new SlotFusible(blockEntity.getInventory(), index, x, y, level);
    }
}
