package com.teramanbr.pearlanium.init;


import com.teramanbr.pearlanium.PearlaniumMod;
import com.teramanbr.pearlanium.list.enums.PearlaniumModToolMaterials;

import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;

public class moditems {
    public static final Item BRUTE_PEARLANIUM = register( "brute_pearlanium", new Item(new Item.Settings()));
    public static final Item PEARLANIUM_UPGRADE_SMITHING_TEMPLATE = register( "pearlanium_upgrade_smithing_template", new Item(new Item.Settings()));
    public static final Item PEARLANIUM_INGOT = register( "pearlanium_ingot", new Item(new Item.Settings()));

    public static final SwordItem PEARLANIUM_SWORD = register("pearlanium_sword",
    new SwordItem(PearlaniumModToolMaterials.PEARLANIUM, new Item.Settings()
    .attributeModifiers(SwordItem.createAttributeModifiers(PearlaniumModToolMaterials.PEARLANIUM, 3, -2.4f))));

    public static final PickaxeItem PEARLANIUM_PICKAXE = register("pearlanium_pickaxe",
    new PickaxeItem(PearlaniumModToolMaterials.PEARLANIUM, new Item.Settings()
    .attributeModifiers(PickaxeItem.createAttributeModifiers(PearlaniumModToolMaterials.PEARLANIUM, 1, -2.8F))));

    public static final AxeItem PEARLANIUM_AXE = register("pearlanium_axe",
    new AxeItem(PearlaniumModToolMaterials.PEARLANIUM, new Item.Settings()
    .attributeModifiers(AxeItem.createAttributeModifiers(PearlaniumModToolMaterials.PEARLANIUM, 5, -3.0F))));

    public static final ShovelItem PEARLANIUM_SHOVEL = register("pearlanium_shovel",
    new ShovelItem(PearlaniumModToolMaterials.PEARLANIUM, new Item.Settings()
    .attributeModifiers(ShovelItem.createAttributeModifiers(PearlaniumModToolMaterials.PEARLANIUM, 1.5F, -3.0F))));

    public static final HoeItem PEARLANIUM_HOE = register("pearlanium_hoe",
    new HoeItem(PearlaniumModToolMaterials.PEARLANIUM, new Item.Settings()
    .attributeModifiers(HoeItem.createAttributeModifiers(PearlaniumModToolMaterials.PEARLANIUM, 0, -3.0F))));





    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, PearlaniumMod.id(name), item);
    }

    public static void load() {
        
    }
}
