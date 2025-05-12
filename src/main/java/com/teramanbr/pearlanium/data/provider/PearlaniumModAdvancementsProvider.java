package com.teramanbr.pearlanium.data.provider;

import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricAdvancementProvider;
import net.minecraft.advancement.Advancement;
import net.minecraft.advancement.AdvancementEntry;
import net.minecraft.advancement.AdvancementFrame;
import net.minecraft.advancement.criterion.InventoryChangedCriterion;
import net.minecraft.predicate.item.ItemPredicate;

import com.teramanbr.pearlanium.init.moditems;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.text.Text;
import net.minecraft.util.Identifier;

import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class PearlaniumModAdvancementsProvider extends FabricAdvancementProvider {
    public PearlaniumModAdvancementsProvider(FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registryLookup) {
        super(output, registryLookup);
    }

    @SuppressWarnings("unused")
    @Override
    public void generateAdvancement(RegistryWrapper.WrapperLookup registryLookup, Consumer<AdvancementEntry> consumer) {
        AdvancementEntry brute_pearlaniumAdvancement = Advancement.Builder.create()
                .display(
                        moditems.BRUTE_PEARLANIUM, // The display icon
                        Text.translatable("advancement.pearlanium.root.title"),
                        Text.translatable("advancement.pearlanium.root.description"),
                        Identifier.of("minecraft:textures/block/sculk_sensor_bottom.png"),
                        AdvancementFrame.TASK,
                        true,
                        true,
                        false
                )
                .criterion("got_pearlanium_item", InventoryChangedCriterion.Conditions.items(moditems.BRUTE_PEARLANIUM)) // Replace with your mod's item
                .build(consumer, "pearlanium:root");

    AdvancementEntry wardens_heartAdvancement = Advancement.Builder.create().parent(brute_pearlaniumAdvancement)
            .display(
                    moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE,
                    Text.translatable("advancement.pearlanium.wardens_heart.title"),
                    Text.translatable("advancement.pearlanium.wardens_heart.description"),
                    null,
                    AdvancementFrame.CHALLENGE,
                    true,
                    true,
                    false
            )
            .criterion("got_warden_heart_item", InventoryChangedCriterion.Conditions.items(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE))
            .build(consumer, "pearlanium:wardens_heart");

    AdvancementEntry fullArmorAdvancement = Advancement.Builder.create().parent(brute_pearlaniumAdvancement)
            .display(
                    // ModItems.PEARLANIUM_CHESTPLATE, // Icon: e.g., your Pearlanium Chestplate
                    moditems.PEARLANIUM_CHESTPLATE, // Placeholder Icon: Replace with your Pearlanium Chestplate
                    Text.translatable("advancement.pearlanium.full_armor.title"),
                    Text.translatable("advancement.pearlanium.full_armor.description"),
                    null, // null background uses the parent's tab background
                    AdvancementFrame.CHALLENGE, // Or TASK/GOAL
                    true, // Show toast
                    true, // Announce to chat
                    false // Hidden until prerequisites are met
            )
            .criterion("has_pearlanium_helmet", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.PEARLANIUM_HELMET).build()
            ))
            .criterion("has_pearlanium_chestplate", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.PEARLANIUM_CHESTPLATE).build()
            ))
            .criterion("has_pearlanium_leggings", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.PEARLANIUM_LEGGINGS).build()
            ))
            .criterion("has_pearlanium_boots", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.PEARLANIUM_BOOTS).build()
            ))
            .build(consumer, "pearlanium:full_pearlanium_armor");


    AdvancementEntry fullReinforcedArmorAdvancement = Advancement.Builder.create().parent(fullArmorAdvancement)
            .display(
                    moditems.REINFORCED_PEARLANIUM_CHESTPLATE,
                    Text.translatable("advancement.pearlanium.reinforced_full_armor.title"),
                    Text.translatable("advancement.pearlanium.reinforced_full_armor.description"),
                    null, // null background uses the parent's tab background
                    AdvancementFrame.CHALLENGE, // Or TASK/GOAL
                    true, // Show toast
                    true, // Announce to chat
                    false // Hidden until prerequisites are met
            )
            .criterion("has_reinforced_pearlanium_helmet", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.REINFORCED_PEARLANIUM_HELMET).build()
            ))
            .criterion("has_reinforced_pearlanium_chestplate", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.REINFORCED_PEARLANIUM_CHESTPLATE).build()
            ))
            .criterion("has_reinforced_pearlanium_leggings", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.REINFORCED_PEARLANIUM_LEGGINGS).build()
            ))
            .criterion("has_reinforced_pearlanium_boots", InventoryChangedCriterion.Conditions.items(
                    ItemPredicate.Builder.create().items(moditems.REINFORCED_PEARLANIUM_BOOTS).build()
            ))
            .build(consumer, "pearlanium:full_reinforced_pearlanium_armor");    
    }
}