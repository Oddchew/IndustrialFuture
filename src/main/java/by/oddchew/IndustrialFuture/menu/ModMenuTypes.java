package by.oddchew.IndustrialFuture.menu;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blockentity.CrusherBlockEntity;
import by.oddchew.IndustrialFuture.blockentity.ElectricFurnaceEntity;
import by.oddchew.IndustrialFuture.blockentity.MachineBlockEntity;
import net.minecraft.core.BlockPos;
import net.minecraft.world.inventory.MenuType;
import net.minecraft.world.level.Level;
import net.minecraftforge.common.extensions.IForgeMenuType;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModMenuTypes {
    public static final DeferredRegister<MenuType<?>> MENU_TYPES =
            DeferredRegister.create(ForgeRegistries.MENU_TYPES, IndustrialFuture.MODID);

    public static final RegistryObject<MenuType<MachineMenu>> MACHINE_MENU =
            MENU_TYPES.register("machine_menu", () ->
                    IForgeMenuType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.level;

                        if (level.getBlockEntity(pos) instanceof MachineBlockEntity blockEntity) {
                            return new MachineMenu(windowId, inv, blockEntity);
                        } else {
                            throw new IllegalStateException("Expected MachineBlockEntity at " + pos + ", but found " + level.getBlockEntity(pos));
                        }
                    }));

    public static final RegistryObject<MenuType<CrusherMenu>> CRUSHER_MENU =
            MENU_TYPES.register("crusher_menu", () ->
                    IForgeMenuType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.level;

                        if (level.getBlockEntity(pos) instanceof CrusherBlockEntity blockEntity) {
                            return new CrusherMenu(windowId, inv, blockEntity);
                        } else {
                            throw new IllegalStateException("Expected CrusherBlockEntity at " + pos + ", but found " + level.getBlockEntity(pos));
                        }
                    }));

    public static final RegistryObject<MenuType<ElectricFurnaceMenu>> ELECTRIC_FURNACE_MENU =
            MENU_TYPES.register("electric_furnace_menu", () ->
                    IForgeMenuType.create((windowId, inv, data) -> {
                        BlockPos pos = data.readBlockPos();
                        Level level = inv.player.level;

                        if (level.getBlockEntity(pos) instanceof ElectricFurnaceEntity blockEntity) {
                            return new ElectricFurnaceMenu(windowId, inv, blockEntity, level);
                        } else {
                            throw new IllegalStateException("Expected CrusherBlockEntity at " + pos + ", but found " + level.getBlockEntity(pos));
                        }
                    }));
}