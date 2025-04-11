package com.teramanbr.pearlanium.data.provider;

import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.init.moditems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.book.RecipeCategory;
import net.minecraft.registry.RegistryWrapper;

import java.util.List;
import java.util.concurrent.CompletableFuture;

public class PearlaniumModRecipeProvider extends FabricRecipeProvider {
    public PearlaniumModRecipeProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    public void generate(RecipeExporter exporter) {
        ShapedRecipeJsonBuilder.create(RecipeCategory.BUILDING_BLOCKS, modblocks.PEARLANIUM_BLOCK)
                .input('E', moditems.PEARLANIUM_INGOT)
                .pattern("EEE")
                .pattern("EEE")
                .pattern("EEE")
                .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
                .offerTo(exporter);

        ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, moditems.PEARLANIUM_INGOT, 9)
                .input(modblocks.PEARLANIUM_BLOCK)
                .criterion(hasItem(modblocks.PEARLANIUM_BLOCK), conditionsFromItem(modblocks.PEARLANIUM_BLOCK))
                .offerTo(exporter);

                ShapelessRecipeJsonBuilder.create(RecipeCategory.MISC, moditems.PEARLANIUM_INGOT)
                .input(moditems.BRUTE_PEARLANIUM, 4)
                .input(Items.EMERALD, 4)             
                .criterion(hasItem(moditems.BRUTE_PEARLANIUM), conditionsFromItem(moditems.BRUTE_PEARLANIUM))
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_INGOT) + "_from_brute_and_emerald");
        

        List<ItemConvertible> modOres = List.of(modblocks.PEARLED_ORE);

        RecipeProvider.offerBlasting(exporter,
                modOres,
                RecipeCategory.MISC,
                moditems.BRUTE_PEARLANIUM,
                1.0f,
                200,
                "blasting_only_pearlanium");
                
    }
}