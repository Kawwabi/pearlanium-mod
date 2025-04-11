package com.teramanbr.pearlanium.list;

import com.teramanbr.pearlanium.PearlaniumMod;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.registry.tag.TagKey;

public class TagList {
    public static class Blocks {
        public static final TagKey<Block> PEARLANIUM_TAG = TagKey.of(RegistryKeys.BLOCK, PearlaniumMod.id("pearlanium"));

        public static final TagKey<Block> INCORRECT_FOR_PEARLANIUM_TOOL =
                TagKey.of(RegistryKeys.BLOCK, PearlaniumMod.id("incorrect_for_pearlanium_tool"));

        public static final TagKey<Block> PEARLANIUM_LOGS = TagKey.of(RegistryKeys.BLOCK, PearlaniumMod.id("pearlanium_logs"));
    }

    public static class Items {
        public static final TagKey<Item> PEARLANIUM_LOGS = TagKey.of(RegistryKeys.ITEM, PearlaniumMod.id("pearlanium_logs"));
    }
}