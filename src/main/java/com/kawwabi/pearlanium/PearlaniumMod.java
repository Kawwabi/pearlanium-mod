package com.kawwabi.pearlanium;

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

import com.kawwabi.pearlanium.init.ModArmorMaterial;
import com.kawwabi.pearlanium.init.PearlaniumModLootTableModifiers;
import com.kawwabi.pearlanium.init.modblocks;
import com.kawwabi.pearlanium.init.moditemgroup;
import com.kawwabi.pearlanium.init.moditems;
import com.kawwabi.pearlanium.init.worldgen.BiomeModificationInit;

public class PearlaniumMod implements ModInitializer {
	public static final String MOD_ID = "pearlanium_mod";

	// this little guy logs stuff so we can debug when things go boom 💥
	// we use the mod id as the name so it's clear who's talking (or screaming)
	public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

	@Override
	public void onInitialize() {
		// load init classes
		moditems.load();
		modblocks.load();
		moditemgroup.load();
		ModArmorMaterial.load();
		BiomeModificationInit.load();

		// load loot table modifiers
		PearlaniumModLootTableModifiers.addLootTableModifiers();

		// event handling - listening for stuff to happen

		// ores - shiny rocks go here
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
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE, moditems.WARDIUM_UPGRADE_SMITHING_TEMPLATE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.INGREDIENTS).register(entries -> {
			entries.addAfter(Items.AMETHYST_SHARD, moditems.CALIBRATED_ECHO_SHARD);
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

		// put wardium tools after pearlanium tools so they don't get lonely
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_SWORD, moditems.WARDIUM_SWORD);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_AXE, moditems.WARDIUM_AXE);
		});

		// tools group for all your digging needs
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_HOE, moditems.WARDIUM_SHOVEL);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.WARDIUM_SHOVEL, moditems.WARDIUM_PICKAXE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.WARDIUM_PICKAXE, moditems.WARDIUM_AXE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.TOOLS).register(entries -> {
			entries.addAfter(moditems.WARDIUM_AXE, moditems.WARDIUM_HOE);
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

		// put wardium armor after pearlanium armor
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.PEARLANIUM_BOOTS, moditems.WARDIUM_HELMET);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.WARDIUM_HELMET, moditems.WARDIUM_CHESTPLATE);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.WARDIUM_CHESTPLATE, moditems.WARDIUM_LEGGINGS);
		});
		ItemGroupEvents.modifyEntriesEvent(ItemGroups.COMBAT).register(entries -> {
			entries.addAfter(moditems.WARDIUM_LEGGINGS, moditems.WARDIUM_BOOTS);
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

		// nothin exploded yet 🎉
		LOGGER.info("Mod loaded!");
	}

	public static Identifier id(String path) {
		return Identifier.of(MOD_ID, path);
	}
}
