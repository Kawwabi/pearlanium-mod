package com.teramanbr.pearlanium.data.provider;

import java.util.concurrent.CompletableFuture;

import com.teramanbr.pearlanium.init.modblocks;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricBlockLootTableProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;


public class PearlaniumModBlockLootTableProvider extends FabricBlockLootTableProvider {
    public PearlaniumModBlockLootTableProvider(FabricDataOutput dataOutput,
            CompletableFuture<WrapperLookup> registryLookup) {
        super(dataOutput, registryLookup);
    }

    @Override
    public void generate() {
        addDrop(modblocks.PEARLANIUM_BLOCK);
        addDrop(modblocks.PEARLED_ORE);

    }

}