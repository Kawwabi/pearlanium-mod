package com.teramanbr.pearlanium.data.provider;

import java.util.concurrent.CompletableFuture;

import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.list.TagList;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricTagProvider.BlockTagProvider;
import net.minecraft.registry.RegistryWrapper.WrapperLookup;
import net.minecraft.registry.tag.BlockTags;

public class PearlaniumModBlockTagProvider extends BlockTagProvider {

    public PearlaniumModBlockTagProvider(FabricDataOutput output, CompletableFuture<WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void configure(WrapperLookup wrapperLookup) {
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                    .add(modblocks.PEARLED_ORE);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(modblocks.PEARLED_ORE);
        
        getOrCreateTagBuilder(BlockTags.NEEDS_DIAMOND_TOOL)
                    .add(modblocks.PEARLANIUM_BLOCK);
        getOrCreateTagBuilder(BlockTags.PICKAXE_MINEABLE)
                    .add(modblocks.PEARLANIUM_BLOCK);
        
        getOrCreateTagBuilder(TagList.Blocks.INCORRECT_FOR_PEARLANIUM_TOOL);
    }

}
