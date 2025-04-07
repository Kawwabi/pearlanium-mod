package com.teramanbr.pearlanium.init;


import com.teramanbr.pearlanium.PearlaniumMod;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class moditems {
    public static final Item BRUTE_PEARLANIUM = register( "brute_pearlanium", new Item(new Item.Settings()));
    public static final Item PEARLANIUM_UPGRADE_SMITHING_TEMPLATE = register( "pearlanium_upgrade_smithing_template", new Item(new Item.Settings()));
    public static final Item PEARLANIUM_INGOT = register( "pearlanium_ingot", new Item(new Item.Settings()));


    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, PearlaniumMod.id(name), item);
    }

    public static void load() {
        
    }
}
