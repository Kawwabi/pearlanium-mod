package com.kawwabi.pearlanium.init;

import com.kawwabi.pearlanium.PearlaniumMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

public class moditemgroup {
    public static final Text PEARLANIUM_TITLE = Text.translatable("itemGroup." + PearlaniumMod.MOD_ID + ".pearlanium_group");

    public static final ItemGroup PEARLANIUM_GROUP = register("pearlanium_group", FabricItemGroup.builder()
            .displayName(PEARLANIUM_TITLE)
            .icon(moditems.BRUTE_PEARLANIUM::getDefaultStack)
            .entries((displayContext, entries) -> {
                // Ores & Raw Materials
                entries.add(modblocks.PEARLED_ORE);
                entries.add(moditems.BRUTE_PEARLANIUM);
                
                // Refined Materials
                entries.add(moditems.PEARLANIUM_INGOT);
                entries.add(modblocks.PEARLANIUM_BLOCK);
                entries.add(moditems.CALIBRATED_ECHO_SHARD);
                
                // Smithing Templates
                entries.add(moditems.PEARLANIUM_UPGRADE_SMITHING_TEMPLATE);
                entries.add(moditems.WARDIUM_UPGRADE_SMITHING_TEMPLATE);
                
                // Pearlanium Tools (Swords, Axes, Pickaxes, Shovels, Hoes)
                entries.add(moditems.PEARLANIUM_SWORD);
                entries.add(moditems.PEARLANIUM_AXE);
                entries.add(moditems.PEARLANIUM_PICKAXE);
                entries.add(moditems.PEARLANIUM_SHOVEL);
                entries.add(moditems.PEARLANIUM_HOE);
                
                // Pearlanium Armor (Helmet, Chestplate, Leggings, Boots)
                entries.add(moditems.PEARLANIUM_HELMET);
                entries.add(moditems.PEARLANIUM_CHESTPLATE);
                entries.add(moditems.PEARLANIUM_LEGGINGS);
                entries.add(moditems.PEARLANIUM_BOOTS);
                
                // Wardium Tools (Swords, Axes, Pickaxes, Shovels, Hoes)
                entries.add(moditems.WARDIUM_SWORD);
                entries.add(moditems.WARDIUM_AXE);
                entries.add(moditems.WARDIUM_PICKAXE);
                entries.add(moditems.WARDIUM_SHOVEL);
                entries.add(moditems.WARDIUM_HOE);
                
                // Wardium Armor (Helmet, Chestplate, Leggings, Boots)
                entries.add(moditems.WARDIUM_HELMET);
                entries.add(moditems.WARDIUM_CHESTPLATE);
                entries.add(moditems.WARDIUM_LEGGINGS);
                entries.add(moditems.WARDIUM_BOOTS);
            })
            .build());

    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, PearlaniumMod.id(name), itemGroup);
    }

    public static void load() {}
}