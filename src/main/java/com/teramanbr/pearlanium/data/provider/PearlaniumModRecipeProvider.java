package com.teramanbr.pearlanium.data.provider;

import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.init.moditems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricRecipeProvider;
import net.minecraft.data.server.recipe.RecipeExporter;
import net.minecraft.data.server.recipe.RecipeProvider;
import net.minecraft.data.server.recipe.ShapedRecipeJsonBuilder;
import net.minecraft.data.server.recipe.ShapelessRecipeJsonBuilder;
import net.minecraft.data.server.recipe.SmithingTransformRecipeJsonBuilder;
import net.minecraft.item.ItemConvertible;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
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

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_PICKAXE), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.PEARLANIUM_PICKAXE // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_PICKAXE), conditionsFromItem(Items.NETHERITE_PICKAXE))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_PICKAXE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_SHOVEL), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.PEARLANIUM_SHOVEL // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_SHOVEL), conditionsFromItem(Items.NETHERITE_SHOVEL))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_SHOVEL) + "_smithing");
        
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_AXE), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.PEARLANIUM_AXE // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_AXE), conditionsFromItem(Items.NETHERITE_AXE))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_AXE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_HOE), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.PEARLANIUM_HOE // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_HOE), conditionsFromItem(Items.NETHERITE_HOE))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_HOE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_SWORD), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.PEARLANIUM_SWORD // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_SWORD), conditionsFromItem(Items.NETHERITE_SWORD))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_SWORD) + "_smithing");

        

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