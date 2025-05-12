package com.teramanbr.pearlanium.data.provider;

import com.teramanbr.pearlanium.init.moditems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.registry.tag.ItemTags;

import java.util.concurrent.CompletableFuture;

public class PearlaniumModItemTagProvider extends FabricTagProvider<Item> {
    public PearlaniumModItemTagProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, RegistryKeys.ITEM, registriesFuture);
    }

    @Override
    protected void configure(RegistryWrapper.WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(moditems.PEARLANIUM_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(moditems.PEARLANIUM_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(moditems.PEARLANIUM_SHOVEL);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(moditems.PEARLANIUM_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(moditems.PEARLANIUM_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(moditems.PEARLANIUM_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(moditems.PEARLANIUM_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(moditems.PEARLANIUM_CHESTPLATE);
        
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(moditems.PEARLANIUM_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.SWORDS)
                .add(moditems.REINFORCED_PEARLANIUM_SWORD);

        getOrCreateTagBuilder(ItemTags.PICKAXES)
                .add(moditems.REINFORCED_PEARLANIUM_PICKAXE);

        getOrCreateTagBuilder(ItemTags.SHOVELS)
                .add(moditems.REINFORCED_PEARLANIUM_SHOVEL);

        getOrCreateTagBuilder(ItemTags.AXES)
                .add(moditems.REINFORCED_PEARLANIUM_AXE);

        getOrCreateTagBuilder(ItemTags.HOES)
                .add(moditems.REINFORCED_PEARLANIUM_HOE);

        getOrCreateTagBuilder(ItemTags.HEAD_ARMOR)
                .add(moditems.REINFORCED_PEARLANIUM_HELMET);

        getOrCreateTagBuilder(ItemTags.CHEST_ARMOR)
                .add(moditems.REINFORCED_PEARLANIUM_CHESTPLATE);

        getOrCreateTagBuilder(ItemTags.LEG_ARMOR)
                .add(moditems.REINFORCED_PEARLANIUM_CHESTPLATE);
        
        getOrCreateTagBuilder(ItemTags.FOOT_ARMOR)
                .add(moditems.REINFORCED_PEARLANIUM_CHESTPLATE);
    }
}
