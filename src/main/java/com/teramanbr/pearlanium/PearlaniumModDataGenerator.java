package com.teramanbr.pearlanium;

import com.teramanbr.pearlanium.data.generator.PearlaniumModWorldGenerator;
import com.teramanbr.pearlanium.data.provider.PearlaniumModBlockLootTableProvider;
import com.teramanbr.pearlanium.data.provider.PearlaniumModBlockTagProvider;
import com.teramanbr.pearlanium.data.provider.PearlaniumModModelProvider;
import com.teramanbr.pearlanium.data.provider.PearlaniumModRecipeProvider;
import com.teramanbr.pearlanium.init.worldgen.ConfiguredFeatureInit;
import com.teramanbr.pearlanium.init.worldgen.PlacedFeatureInit;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.minecraft.registry.RegistryBuilder;
import net.minecraft.registry.RegistryKeys;

public class PearlaniumModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PearlaniumModModelProvider::new);
        pack.addProvider(PearlaniumModBlockLootTableProvider::new);
        pack.addProvider(PearlaniumModBlockTagProvider::new);
        pack.addProvider(PearlaniumModWorldGenerator::new);
        pack.addProvider(PearlaniumModRecipeProvider::new);
    }

    @Override
    public void buildRegistry(RegistryBuilder registryBuilder) {
        registryBuilder.addRegistry(RegistryKeys.CONFIGURED_FEATURE, ConfiguredFeatureInit::bootstrap);
        registryBuilder.addRegistry(RegistryKeys.PLACED_FEATURE, PlacedFeatureInit::bootstrap);
    }
    
}
