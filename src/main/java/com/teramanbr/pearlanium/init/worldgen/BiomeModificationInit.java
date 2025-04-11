package com.teramanbr.pearlanium.init.worldgen;

import net.fabricmc.fabric.api.biome.v1.BiomeModifications;
import net.fabricmc.fabric.api.biome.v1.BiomeSelectors;
import net.minecraft.world.gen.GenerationStep;

public class BiomeModificationInit {
    public static void load() {
        BiomeModifications.addFeature(
            BiomeSelectors.foundInTheEnd(),
            GenerationStep.Feature.UNDERGROUND_ORES,
            PlacedFeatureInit.PEARLED_ORE_KEY
            );
    }
}
