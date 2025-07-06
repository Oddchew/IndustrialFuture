package by.oddchew.IndustrialFuture.worldgen;

import net.minecraft.core.Registry;
import net.minecraft.world.level.levelgen.VerticalAnchor;
import net.minecraft.world.level.levelgen.placement.*;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.RegistryObject;
import by.oddchew.IndustrialFuture.IndustrialFuture;

import java.util.List;

public class ModPlacedFeatures {
    public static final DeferredRegister<PlacedFeature> PLACED_FEATURES =
            DeferredRegister.create(Registry.PLACED_FEATURE_REGISTRY, IndustrialFuture.MODID);

    // Размещение серной руды
    public static final RegistryObject<PlacedFeature> SULFUR_ORE_PLACED = PLACED_FEATURES.register("sulfur_ore_placed",
            () -> {
                System.out.println("Registering sulfur_ore_placed with feature: " + ModConfiguredFeatures.SULFUR_ORE.getHolder().get());
                return new PlacedFeature(ModConfiguredFeatures.SULFUR_ORE.getHolder().get(),
                        commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(25))));
            });; // Настраиваемый диапазон высот

    //public static final RegistryObject<PlacedFeature> END_SULFUR_ORE_PLACED = PLACED_FEATURES.register("end_sulfur_ore_placed",
    //        () -> new PlacedFeature(ModConfiguredFeatures.END_SULFUR_ORE.getHolder().get(), commonOrePlacement(15,
    //                HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40)))));

    //public static final RegistryObject<PlacedFeature> NETHER_SULFUR_ORE_PLACED = PLACED_FEATURES.register("nether_sulfur_ore_placed",
    //        () -> new PlacedFeature(ModConfiguredFeatures.NETHER_SULFUR_ORE.getHolder().get(), commonOrePlacement(15,
    //                HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40)))));


    // Размещение оловянной руды
    public static final RegistryObject<PlacedFeature> TIN_ORE_PLACED = PLACED_FEATURES.register("tin_ore_placed",
            () -> {
                System.out.println("Registering sulfur_ore_placed with feature: " + ModConfiguredFeatures.TIN_ORE.getHolder().get());
                return new PlacedFeature(ModConfiguredFeatures.TIN_ORE.getHolder().get(),
                        commonOrePlacement(15, HeightRangePlacement.triangle(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40))));
            });; // Настраиваемый диапазон высот

    //public static final RegistryObject<PlacedFeature> END_SULFUR_ORE_PLACED = PLACED_FEATURES.register("end_sulfur_ore_placed",
    //        () -> new PlacedFeature(ModConfiguredFeatures.END_SULFUR_ORE.getHolder().get(), commonOrePlacement(15,
    //                HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40)))));

    //public static final RegistryObject<PlacedFeature> NETHER_SULFUR_ORE_PLACED = PLACED_FEATURES.register("nether_sulfur_ore_placed",
    //        () -> new PlacedFeature(ModConfiguredFeatures.NETHER_SULFUR_ORE.getHolder().get(), commonOrePlacement(15,
    //                HeightRangePlacement.uniform(VerticalAnchor.absolute(-60), VerticalAnchor.absolute(40)))));

    public static List<PlacementModifier> orePlacement(PlacementModifier p_195347_, PlacementModifier p_195348_) {
        return List.of(p_195347_, InSquarePlacement.spread(), p_195348_, BiomeFilter.biome());
    }

    public static List<PlacementModifier> commonOrePlacement(int p_195344_, PlacementModifier p_195345_) {
        return orePlacement(CountPlacement.of(p_195344_), p_195345_);
    }

    public static List<PlacementModifier> rareOrePlacement(int p_195350_, PlacementModifier p_195351_) {
        return orePlacement(RarityFilter.onAverageOnceEvery(p_195350_), p_195351_);
    }

}