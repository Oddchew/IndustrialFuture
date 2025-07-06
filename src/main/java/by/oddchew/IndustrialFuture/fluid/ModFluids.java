package by.oddchew.IndustrialFuture.fluid;

import by.oddchew.IndustrialFuture.IndustrialFuture;
import by.oddchew.IndustrialFuture.fluid.custom.OilFluidType;
import by.oddchew.IndustrialFuture.fluid.custom.PetroleumFluedType;
import net.minecraft.world.level.block.LiquidBlock;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.material.FlowingFluid;
import net.minecraft.world.level.material.Fluid;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraftforge.fluids.FluidType;
import net.minecraftforge.fluids.ForgeFlowingFluid;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import net.minecraftforge.registries.RegistryObject;
import net.minecraft.world.level.block.Block;

import static by.oddchew.IndustrialFuture.items.OilBucketRegistry.OIL_BUCKET;
import static by.oddchew.IndustrialFuture.items.PetroleumBucketRegistry.PETROLEUM_BUCKET;


public class ModFluids {
    public static final DeferredRegister<Fluid> FLUIDS = DeferredRegister.create(ForgeRegistries.FLUIDS, IndustrialFuture.MODID);
    public static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, IndustrialFuture.MODID);
    public static final DeferredRegister<FluidType> FLUID_TYPES = DeferredRegister.create(ForgeRegistries.Keys.FLUID_TYPES, IndustrialFuture.MODID);

    public static final RegistryObject<FlowingFluid> OIL_SOURCE = FLUIDS.register("oil",
            () -> new ForgeFlowingFluid.Source(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FlowingFluid> OIL_FLOWING = FLUIDS.register("flowing_oil",
            () -> new ForgeFlowingFluid.Flowing(ModFluids.OIL_PROPERTIES));

    public static final RegistryObject<FluidType> OIL_FLUID_TYPE =
            FLUID_TYPES.register("oil_fluid_type", OilFluidType::new);


    public static final RegistryObject<LiquidBlock> OIL_BLOCK = BLOCKS.register("oil_block",
            () -> new LiquidBlock(() -> OIL_SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA
            ).noCollission().strength(100f).noLootTable()));


    public static final ForgeFlowingFluid.Properties OIL_PROPERTIES = new ForgeFlowingFluid.Properties(
            OIL_FLUID_TYPE, OIL_SOURCE, OIL_FLOWING)
            .bucket(OIL_BUCKET)
            .block(OIL_BLOCK);


    public static final RegistryObject<FlowingFluid> PETROLEUM_SOURCE = FLUIDS.register("petroleum",
            () -> new ForgeFlowingFluid.Source(by.oddchew.IndustrialFuture.fluid.ModFluids.PETROLEUM_PROPERTIES));

    public static final RegistryObject<FlowingFluid> PETROLEUM_FLOWING = FLUIDS.register("flowing_petroleum",
            () -> new ForgeFlowingFluid.Flowing(by.oddchew.IndustrialFuture.fluid.ModFluids.PETROLEUM_PROPERTIES));

    public static final RegistryObject<FluidType> PETROLEUM_FLUID_TYPE =
            FLUID_TYPES.register("petroleum_fluid_type", PetroleumFluedType::new);


    public static final RegistryObject<LiquidBlock> PETROLEUM_BLOCK = BLOCKS.register("petroleum_block",
            () -> new LiquidBlock(() -> PETROLEUM_SOURCE.get(), BlockBehaviour.Properties.of(Material.LAVA
            ).noCollission().strength(100f).noLootTable()));


    public static final ForgeFlowingFluid.Properties PETROLEUM_PROPERTIES = new ForgeFlowingFluid.Properties(
            PETROLEUM_FLUID_TYPE, PETROLEUM_SOURCE, PETROLEUM_FLOWING)
            .bucket(PETROLEUM_BUCKET)
            .block(PETROLEUM_BLOCK);
}

