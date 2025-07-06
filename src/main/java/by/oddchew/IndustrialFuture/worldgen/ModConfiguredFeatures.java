package by.oddchew.IndustrialFuture.worldgen;

import by.oddchew.IndustrialFuture.blocks.SulfurOre;
import by.oddchew.IndustrialFuture.blocks.TinOre;
import com.google.common.base.Supplier;
import com.google.common.base.Suppliers;
import net.minecraft.core.Registry;
import net.minecraft.data.worldgen.features.OreFeatures;
import net.minecraft.world.level.block.Blocks;
import net.minecraft.world.level.levelgen.feature.ConfiguredFeature;
import net.minecraft.world.level.levelgen.feature.Feature;
import net.minecraft.world.level.levelgen.feature.configurations.OreConfiguration;
import net.minecraft.world.level.levelgen.structure.templatesystem.BlockMatchTest;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import by.oddchew.IndustrialFuture.IndustrialFuture;

import java.util.List;

public class ModConfiguredFeatures {
    public static final DeferredRegister<ConfiguredFeature<?, ?>> CONFIGURED_FEATURES =
            DeferredRegister.create(Registry.CONFIGURED_FEATURE_REGISTRY, IndustrialFuture.MODID);


    // Конфигурации для серы (sulfur)
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_SULFUR_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, SulfurOre.SULFUR_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, SulfurOre.DEEPSLATE_SULFUR_ORE.get().defaultBlockState())));
    //public static final Supplier<List<OreConfiguration.TargetBlockState>> END_SULFUR_ORES = Suppliers.memoize(() -> List.of(
    //        OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), InitBlocksL192.ENDSTONE_SULFUR_ORE.get().defaultBlockState()))); // Предполагается, что блок ENDSTONE_SULFUR_ORE определен
    //public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_SULFUR_ORES = Suppliers.memoize(() -> List.of(
    //        OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, InitBlocksL192.NETHERRACK_SULFUR_ORE.get().defaultBlockState()))); // Предполагается, что блок NETHERRACK_SULFUR_ORE определен

    public static final RegistryObject<ConfiguredFeature<?, ?>> SULFUR_ORE = CONFIGURED_FEATURES.register("sulfur_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_SULFUR_ORES.get(), 7)));
    //public static final RegistryObject<ConfiguredFeature<?, ?>> END_SULFUR_ORE = CONFIGURED_FEATURES.register("end_sulfur_ore",
    //        () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_SULFUR_ORES.get(), 9)));
    //public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_SULFUR_ORE = CONFIGURED_FEATURES.register("nether_sulfur_ore",
    //        () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_SULFUR_ORES.get(), 9)));

    // Конфигурации для олова (tin)
    public static final Supplier<List<OreConfiguration.TargetBlockState>> OVERWORLD_TIN_ORES = Suppliers.memoize(() -> List.of(
            OreConfiguration.target(OreFeatures.STONE_ORE_REPLACEABLES, TinOre.TIN_ORE.get().defaultBlockState()),
            OreConfiguration.target(OreFeatures.DEEPSLATE_ORE_REPLACEABLES, TinOre.DEEPSLATE_TIN_ORE.get().defaultBlockState())));
    //public static final Supplier<List<OreConfiguration.TargetBlockState>> END_TIN_ORES = Suppliers.memoize(() -> List.of(
    //        OreConfiguration.target(new BlockMatchTest(Blocks.END_STONE), InitBlocksL192.ENDSTONE_TIN_ORE.get().defaultBlockState()))); // Предполагается, что блок ENDSTONE_SULFUR_ORE определен
    //public static final Supplier<List<OreConfiguration.TargetBlockState>> NETHER_TIN_ORES = Suppliers.memoize(() -> List.of(
    //        OreConfiguration.target(OreFeatures.NETHER_ORE_REPLACEABLES, InitBlocksL192.NETHERRACK_TIN_ORE.get().defaultBlockState()))); // Предполагается, что блок NETHERRACK_SULFUR_ORE определен

    public static final RegistryObject<ConfiguredFeature<?, ?>> TIN_ORE = CONFIGURED_FEATURES.register("tin_ore",
            () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(OVERWORLD_TIN_ORES.get(), 7)));
    //public static final RegistryObject<ConfiguredFeature<?, ?>> END_TIN_ORE = CONFIGURED_FEATURES.register("end_tin_ore",
    //        () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(END_TIN_ORES.get(), 9)));
    //public static final RegistryObject<ConfiguredFeature<?, ?>> NETHER_TIN_ORE = CONFIGURED_FEATURES.register("nether_tin_ore",
    //        () -> new ConfiguredFeature<>(Feature.ORE, new OreConfiguration(NETHER_TIN_ORES.get(), 9)));

}