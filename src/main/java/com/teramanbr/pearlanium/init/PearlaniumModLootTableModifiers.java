package com.teramanbr.pearlanium.init;

import net.fabricmc.fabric.api.loot.v3.LootTableEvents;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.condition.RandomChanceLootCondition;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.util.Identifier;

public class PearlaniumModLootTableModifiers {
    private static final Identifier WARDEN_ID 
    = Identifier.of("minecraft", "entities/warden");

    public static void addLootTableModifiers() {
        LootTableEvents.MODIFY.register((key, tableBuilder, source, registry) -> {
            if(WARDEN_ID.equals(key.getValue())) {
                LootPool.Builder poolBuilder = LootPool.builder()
                .rolls(ConstantLootNumberProvider.create(1))
                .conditionally(RandomChanceLootCondition.builder(0.25f))
                .with(ItemEntry.builder(moditems.REINFORCED_PEARLANIUM_UPGRADE_SMITHING_TEMPLATE));
                tableBuilder.pool(poolBuilder);
            }
        });
    }
}   
