package com.teramanbr.pearlanium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTables;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.SetCountLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teramanbr.pearlanium.init.ModArmorMaterial;
import com.teramanbr.pearlanium.init.PearlaniumModLootTableModifiers;
import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.init.moditemgroup;
import com.teramanbr.pearlanium.init.moditems;
import com.teramanbr.pearlanium.init.worldgen.BiomeModificationInit;

public class PearlaniumMod implements ModInitializer {
	public static final String MOD_ID = "pearlanium_mod";

	// This logger is used to write text to the console and the log file.
	// It is considered best practice to use your mod id as the logger's name.
	// That way, it's clear which mod wrote info, warnings, and errors.
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// This code runs as soon as Minecraft is in a mod-load-ready state.
		// However, some things (like resources) may still be uninitialized.
		// Proceed with mild caution.

		// load init classes
		moditems.load();
		modblocks.load();
		moditemgroup.load();
		ModArmorMaterial.load();
		BiomeModificationInit.load();

		// load loot table modifiers
		PearlaniumModLootTableModifiers.addLootTableModifiers();

		// event handling

		// ores
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.addAfter(Items.ANCIENT_DEBRIS, modblocks.PEARLED_ORE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.ANCIENT_DEBRIS, modblocks.PEARLED_ORE);
		});
		
		// items
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_SCRAP, moditems.BRUTE_PEARLANIUM);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_INGOT, moditems.PEARLANIUM_INGOT);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE);
		});

		// blocks
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.addAfter(Items.NETHERITE_BLOCK, modblocks.PEARLANIUM_BLOCK);
		});

		// tools
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(Items.NETHERITE_SWORD, moditems.PEARLANIUM_SWORD);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(Items.NETHERITE_AXE, moditems.PEARLANIUM_AXE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(Items.NETHERITE_HOE, moditems.PEARLANIUM_SHOVEL);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_SHOVEL, moditems.PEARLANIUM_PICKAXE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_PICKAXE, moditems.PEARLANIUM_AXE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_AXE, moditems.PEARLANIUM_HOE);
		});

		// armor
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(Items.NETHERITE_BOOTS, moditems.PEARLANIUM_HELMET);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_HELMET, moditems.PEARLANIUM_CHESTPLATE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_CHESTPLATE, moditems.PEARLANIUM_LEGGINGS);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_LEGGINGS, moditems.PEARLANIUM_BOOTS);
		});


    LootTableEvents.MODIFY.register((key, tableBuilder, source, registries) -> {
        if (LootTables.END_CITY_TREASURE_CHEST.equals(key)) {
            LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .conditionally(RandomChanceLootCondition.builder(0.25f))
                .with(ItemEntry.builder(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE)
                		.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 2.0f))));
            tableBuilder.pool(poolBuilder);
			LootPool.Builder poolBuilder2 = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .conditionally(RandomChanceLootCondition.builder(0.16f))
                .with(ItemEntry.builder(moditems.PEARLANIUM_INGOT)
                		.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            tableBuilder.pool(poolBuilder2);
			LootPool.Builder poolBuilder3 = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1.0f))
                .conditionally(RandomChanceLootCondition.builder(0.244f))
                .with(ItemEntry.builder(moditems.BRUTE_PEARLANIUM)
                		.apply(SetCountLootFunction.builder(UniformLootNumberProvider.create(1.0f, 1.0f))));
            tableBuilder.pool(poolBuilder3);
        }
    });

		//test message
		LOGGER.info("Mod loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}