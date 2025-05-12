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
        // Items
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

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE)
                .input('E', Items.EMERALD)
                .input('P', moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE)
                .input('N', Items.END_STONE)
                .pattern("EPE")
                .pattern("ENE")
                .pattern("EEE")
                .criterion(hasItem(Items.EMERALD), conditionsFromItem(Items.EMERALD))
                .criterion(hasItem(Items.END_STONE), conditionsFromItem(Items.END_STONE))
                .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
                .offerTo(exporter);

        ShapedRecipeJsonBuilder.create(RecipeCategory.MISC, moditems.CALIBRATED_ECHO_SHARD)
                .input('E', Items.ECHO_SHARD)
                .input('A', Items.AMETHYST_BLOCK)
                .pattern("E  ")
                .pattern(" A ")
                .pattern("  E")
                .criterion(hasItem(Items.ECHO_SHARD), conditionsFromItem(Items.ECHO_SHARD))
                .criterion(hasItem(Items.AMETHYST_BLOCK), conditionsFromItem(Items.AMETHYST_BLOCK))
                .offerTo(exporter);


        // Pearlanium Tools
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


        // Reinforced Pearlanium Tools
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_PICKAXE), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.REINFORCED_PEARLANIUM_PICKAXE // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_PICKAXE), conditionsFromItem(moditems.PEARLANIUM_PICKAXE))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_PICKAXE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_AXE), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.REINFORCED_PEARLANIUM_AXE // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_AXE), conditionsFromItem(moditems.PEARLANIUM_AXE))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_AXE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_SHOVEL), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.REINFORCED_PEARLANIUM_SHOVEL // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_SHOVEL), conditionsFromItem(moditems.PEARLANIUM_SHOVEL))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_SHOVEL) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_HOE), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.TOOLS, // Appropriate category
                moditems.REINFORCED_PEARLANIUM_HOE // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_HOE), conditionsFromItem(moditems.PEARLANIUM_HOE))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_HOE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_SWORD), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.COMBAT, // Appropriate category
                moditems.REINFORCED_PEARLANIUM_SWORD // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_SWORD), conditionsFromItem(moditems.PEARLANIUM_SWORD))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_SWORD) + "_smithing");


        // Pearlanium Armor
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_HELMET), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.PEARLANIUM_HELMET // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_HELMET), conditionsFromItem(Items.NETHERITE_HELMET))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_HELMET) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_CHESTPLATE), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.PEARLANIUM_CHESTPLATE // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_CHESTPLATE), conditionsFromItem(Items.NETHERITE_CHESTPLATE))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_CHESTPLATE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_LEGGINGS), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.PEARLANIUM_LEGGINGS // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_LEGGINGS), conditionsFromItem(Items.NETHERITE_LEGGINGS))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_LEGGINGS) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(Items.NETHERITE_BOOTS), // Base input (second slot)
                Ingredient.ofItems(moditems.PEARLANIUM_INGOT), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.PEARLANIUM_BOOTS // Resulting item
        )
        .criterion(hasItem(Items.NETHERITE_BOOTS), conditionsFromItem(Items.NETHERITE_BOOTS))
        .criterion(hasItem(moditems.PEARLANIUM_INGOT), conditionsFromItem(moditems.PEARLANIUM_INGOT))
        .criterion(hasItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.PEARLANIUM_BOOTS) + "_smithing");


        // Reinforced Pearlanium Armor
        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_HELMET), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.REINFORCED_PEARLANIUM_HELMET // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_HELMET), conditionsFromItem(moditems.PEARLANIUM_HELMET))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_HELMET) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_CHESTPLATE), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.REINFORCED_PEARLANIUM_CHESTPLATE // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_CHESTPLATE), conditionsFromItem(moditems.PEARLANIUM_CHESTPLATE))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_CHESTPLATE) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_LEGGINGS), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.REINFORCED_PEARLANIUM_LEGGINGS // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_LEGGINGS), conditionsFromItem(moditems.PEARLANIUM_LEGGINGS))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_LEGGINGS) + "_smithing");

        SmithingTransformRecipeJsonBuilder.create(
                Ingredient.ofItems(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), // Template input (first slot)
                Ingredient.ofItems(moditems.PEARLANIUM_BOOTS), // Base input (second slot)
                Ingredient.ofItems(moditems.CALIBRATED_ECHO_SHARD), // Addition input (third slot)
                RecipeCategory.COMBAT,
                moditems.REINFORCED_PEARLANIUM_BOOTS // Resulting item
        )
        .criterion(hasItem(moditems.PEARLANIUM_BOOTS), conditionsFromItem(moditems.PEARLANIUM_BOOTS))
        .criterion(hasItem(moditems.CALIBRATED_ECHO_SHARD), conditionsFromItem(moditems.CALIBRATED_ECHO_SHARD))
        .criterion(hasItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE), conditionsFromItem(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
        .offerTo(exporter, getRecipeName(moditems.REINFORCED_PEARLANIUM_BOOTS) + "_smithing");

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