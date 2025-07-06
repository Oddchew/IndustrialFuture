package by.oddchew.IndustrialFuture.blockentity;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.blocks.ModBlocks;
import net.minecraft.core.BlockPos;
import net.minecraft.world.level.block.entity.BlockEntityType;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;

public class ModBlockEntities {
    public static final DeferredRegister<BlockEntityType<?>> BLOCK_ENTITIES =
            DeferredRegister.create(ForgeRegistries.BLOCK_ENTITY_TYPES, IndustrialFuture.MODID);

    public static final RegistryObject<BlockEntityType<MachineBlockEntity>> MACHINE =
            BLOCK_ENTITIES.register("machine", () ->
                    BlockEntityType.Builder.of(MachineBlockEntity::new, ModBlocks.MACHINE.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<CrusherBlockEntity>> CRUSHER =
            BLOCK_ENTITIES.register("crusher", () ->
                    BlockEntityType.Builder.of(CrusherBlockEntity::new, ModBlocks.CRUSHER.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<CableBlockEntity>> COPPER_CABLE =
            BLOCK_ENTITIES.register("copper_cable",
                    () -> BlockEntityType.Builder.of(CableBlockEntity::create, ModBlocks.COPPER_CABLE.get())
                            .build(null));

    public static final RegistryObject<BlockEntityType<ElectricFurnaceEntity>> ELECRTIC_FURNACE =
            BLOCK_ENTITIES.register("electric_furnace",
                    () -> BlockEntityType.Builder.of(ElectricFurnaceEntity::new, ModBlocks.ELECRTIC_FURNACE.get())
                            .build(null));
}