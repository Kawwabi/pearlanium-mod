package com.kawwabi.pearlanium.init;

import com.kawwabi.pearlanium.PearlaniumMod;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.Block;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class modblocks {
    public static final Block PEARLED_ORE = registerWithItem("pearled_ore", 
        new Block(AbstractBlock.Settings.create()
        .strength(30.0F, 50.0F)
        .requiresTool()), new Item.Settings().fireproof());

        public static final Block PEARLANIUM_BLOCK = registerWithItem("pearlanium_block", 
        new Block(AbstractBlock.Settings.create()
        .strength(40.0F, 50.0F)
        .requiresTool()), new Item.Settings().fireproof());

    public static <T extends Block> T register(String name, T block) {
        return Registry.register(Registries.BLOCK, PearlaniumMod.id(name), block);
    }

    public static <T extends Block> T registerWithItem(String name, T block, Item.Settings settings) {
        T registered = register(name, block);
        moditems.register(name, new BlockItem(registered, settings));
        return registered;
    }

    public static <T extends Block> T registerWithItem(String name, T block) {
        return registerWithItem(name, block, new Item.Settings());
    }

    public static void load() {

    }
}
