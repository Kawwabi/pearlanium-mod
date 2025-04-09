package com.teramanbr.pearlanium;

import com.teramanbr.pearlanium.data.provider.PearlaniumModBlockLootTableProvider;
import com.teramanbr.pearlanium.data.provider.PearlaniumModBlockTagProvider;
import com.teramanbr.pearlanium.data.provider.PearlaniumModModelProvider;

import net.fabricmc.fabric.api.datagen.v1.DataGeneratorEntrypoint;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;

public class PearlaniumModDataGenerator implements DataGeneratorEntrypoint {
    @Override
    public void onInitializeDataGenerator(FabricDataGenerator fabricDataGenerator) {
        FabricDataGenerator.Pack pack = fabricDataGenerator.createPack();
        pack.addProvider(PearlaniumModModelProvider::new);
        pack.addProvider(PearlaniumModBlockLootTableProvider::new);
        pack.addProvider(PearlaniumModBlockTagProvider::new);
    }
    
}
