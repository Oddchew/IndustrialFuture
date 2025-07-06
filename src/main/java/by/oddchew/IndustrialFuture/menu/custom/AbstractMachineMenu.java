package by.oddchew.IndustrialFuture.menu.custom;

import by.oddchew.IndustrialFuture.blockentity.custom.AbstractMachineBlockEntity;
import by.oddchew.IndustrialFuture.slot.CantPlace;
import by.oddchew.IndustrialFuture.slot.SlotBattery;
import by.oddchew.IndustrialFuture.slot.SlotBurn;
import net.minecraft.world.entity.player.Inventory;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.inventory.AbstractContainerMenu;
import net.minecraft.world.inventory.ContainerLevelAccess;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.inventory.Slot;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.block.Block;
import net.minecraftforge.items.SlotItemHandler;

public abstract class AbstractMachineMenu extends AbstractContainerMenu {
    protected final AbstractMachineBlockEntity blockEntity;
    protected final ContainerLevelAccess levelAccess;
    protected final Inventory inv;

    public AbstractMachineMenu(MenuType<?> menuType, int id, Inventory playerInv, AbstractMachineBlockEntity blockEntity, Block block) {
        super(menuType, id);
        this.blockEntity = blockEntity;
        this.levelAccess = ContainerLevelAccess.create(blockEntity.getLevel(), blockEntity.getBlockPos());
        this.inv = playerInv;
    }

    public AbstractMachineBlockEntity getBlockEntity() {
        return blockEntity;
    }

    // Виртуальные методы для создания слотов
    protected Slot createInputSlot(int index, int x, int y) {
        return new SlotItemHandler(blockEntity.getInventory(), index, x, y);
    }

    protected Slot createBatterySlot(int index, int x, int y) {
        return new SlotBattery(blockEntity.getInventory(), index, x, y);
    }

    protected void addInventorySlots(int inventorySize, int batterySlotX, int batterySlotY, int inputSlotX, int inputSlotY) {
        // Добавляем слот для ввода (индекс 0)
        addSlot(createInputSlot(0, inputSlotX, inputSlotY));

        // Добавляем слот для батареи (индекс 1), если inventorySize > 1
        if (inventorySize > 1) {
            addSlot(createBatterySlot(1, batterySlotX, batterySlotY));
        }

        // Добавляем слоты инвентаря игрока
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    protected void addInventorySlots(int inventorySize, int batterySlotX, int batterySlotY, int inputSlotX, int inputSlotY, int outputSlotX, int outputSlotY) {
        // Добавляем слот для ввода (индекс 0)
        addSlot(createInputSlot(0, inputSlotX, inputSlotY));

        // Добавляем слот для батареи (индекс 1), если inventorySize > 1
        if (inventorySize > 1) {
            addSlot(createBatterySlot(1, batterySlotX, batterySlotY));
        }

        if (inventorySize > 2) {
            addSlot(new CantPlace(blockEntity.getInventory(),2, outputSlotX, outputSlotY));
        }

        // Добавляем слоты инвентаря игрока
        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 9; col++) {
                addSlot(new Slot(inv, col + row * 9 + 9, 8 + col * 18, 84 + row * 18));
            }
        }
        for (int col = 0; col < 9; col++) {
            addSlot(new Slot(inv, col, 8 + col * 18, 142));
        }
    }

    @Override
    public boolean stillValid(Player player) {
        return stillValid(levelAccess, player, blockEntity.getBlockState().getBlock());
    }

    @Override
    public ItemStack quickMoveStack(Player player, int index) {
        ItemStack stack = ItemStack.EMPTY;
        Slot slot = slots.get(index);
        if (slot.hasItem()) {
            ItemStack stackInSlot = slot.getItem();
            stack = stackInSlot.copy();
            int inventorySize = blockEntity.getInventory().getSlots();
            if (index < inventorySize) {
                if (!moveItemStackTo(stackInSlot, inventorySize, slots.size(), true)) {
                    return ItemStack.EMPTY;
                }
            } else if (!moveItemStackTo(stackInSlot, 0, inventorySize, false)) {
                return ItemStack.EMPTY;
            }
            if (stackInSlot.isEmpty()) {
                slot.set(ItemStack.EMPTY);
            } else {
                slot.setChanged();
            }
        }
        return stack;
    }
}