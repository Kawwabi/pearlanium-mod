package com.teramanbr.pearlanium.data.provider;

import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.init.moditems;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;

public class PearlaniumModModelProvider extends FabricModelProvider {

    public PearlaniumModModelProvider(FabricDataOutput output) {
        super(output);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleCubeAll(modblocks.PEARLANIUM_BLOCK);
        blockStateModelGenerator.registerSimpleCubeAll(modblocks.PEARLED_ORE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        itemModelGenerator.register(moditems.BRUTE_PEARLANIUM, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_INGOT, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_HELMET, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_CHESTPLATE, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_LEGGINGS, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_BOOTS, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE, Models.GENERATED);
        itemModelGenerator.register(moditems.PEARLANIUM_SWORD, Models.HANDHELD);
        itemModelGenerator.register(moditems.PEARLANIUM_PICKAXE, Models.HANDHELD);
        itemModelGenerator.register(moditems.PEARLANIUM_AXE, Models.HANDHELD);
        itemModelGenerator.register(moditems.PEARLANIUM_SHOVEL, Models.HANDHELD);
        itemModelGenerator.register(moditems.PEARLANIUM_HOE, Models.HANDHELD);
    }
}
