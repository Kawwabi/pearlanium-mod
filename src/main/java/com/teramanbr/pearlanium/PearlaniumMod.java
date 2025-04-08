package com.teramanbr.pearlanium;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.itemgroup.v1.ItemGroupEvents;
import net.minecraft.item.ItemGroups;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.teramanbr.pearlanium.init.modblocks;
import com.teramanbr.pearlanium.init.moditemgroup;
import com.teramanbr.pearlanium.init.moditems;

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

		// event handling
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.NATURAL).register(entries -> {
			entries.addAfter(Items.ANCIENT_DEBRIS, modblocks.PEARLED_ORE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.ANCIENT_DEBRIS, modblocks.PEARLED_ORE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_SCRAP, moditems.BRUTE_PEARLANIUM);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_INGOT, moditems.PEARLANIUM_INGOT);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.NETHERITE_UPGRADE_SMITHING_TEMPLATE, moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.BUILDING_BLOCKS).register(entries -> {
			entries.addAfter(Items.NETHERITE_BLOCK, modblocks.PEARLANIUM_BLOCK);
		});

		//test message
		LOGGER.info("Mod loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}