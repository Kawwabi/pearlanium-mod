package com.teramanbr.pearlanium.init;


import com.teramanbr.pearlanium.PearlaniumMod;
import com.teramanbr.pearlanium.list.enums.PearlaniumModToolMaterials;

import net.minecraft.item.ArmorItem;
import net.minecraft.item.AxeItem;
import net.minecraft.item.HoeItem;
import net.minecraft.item.Item;
import net.minecraft.item.PickaxeItem;
import net.minecraft.item.ShovelItem;
import net.minecraft.item.SwordItem;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.item.SmithingTemplateItem;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.Identifier;
import net.minecraft.util.Util;
import java.util.List;

    public class moditems {

    // Text components for the Pearlanium Upgrade Smithing Template tooltip
    private static final Text PEARLANIUM_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", PearlaniumMod.id("pearlanium_upgrade"))).formatted(Formatting.GRAY);
    private static final Text PEARLANIUM_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.applies_to"))).formatted(Formatting.BLUE);
    private static final Text PEARLANIUM_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.ingredients"))).formatted(Formatting.BLUE);
    private static final Text PEARLANIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.base_slot_description")));
    private static final Text PEARLANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.additions_slot_description")));

// List of item icons to display in the smithing table UI empty slots (similar to Netherite upgrade)
// You'll need textures for these if you want them to display properly.
// Using vanilla empty slot textures as placeholders:
    private static final Identifier EMPTY_SLOT_HELMET_TEXTURE = Identifier.of("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE_TEXTURE = Identifier.of("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS_TEXTURE = Identifier.of("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_SLOT_BOOTS_TEXTURE = Identifier.of("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.of("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.of("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.of("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.of("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.of("item/empty_slot_pickaxe");
    // Assuming your upgrade uses Pearlanium Ingots
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.of("item/empty_slot_ingot"); // Or your Pearlanium Ingot texture path if preferred


    private static final List<Identifier> PEARLANIUM_UPGRADE_BASE_SLOT_TEXTURES = List.of(
        EMPTY_SLOT_HELMET_TEXTURE,
        EMPTY_SLOT_SWORD_TEXTURE,
        EMPTY_SLOT_CHESTPLATE_TEXTURE,
        EMPTY_SLOT_PICKAXE_TEXTURE,
        EMPTY_SLOT_LEGGINGS_TEXTURE,
        EMPTY_SLOT_AXE_TEXTURE,
        EMPTY_SLOT_BOOTS_TEXTURE,
        EMPTY_SLOT_HOE_TEXTURE,
        EMPTY_SLOT_SHOVEL_TEXTURE
    );
    private static final List<Identifier> PEARLANIUM_UPGRADE_ADDITIONAL_SLOT_TEXTURES = List.of(
            EMPTY_SLOT_INGOT_TEXTURE // Represents the Pearlanium Ingot slot
    );

    public static final Item PEARLANIUM_UPGRADE_SMITHING_TEMPLATE = register( "pearlanium_upgrade_smithing_template",
        new SmithingTemplateItem(
                PEARLANIUM_UPGRADE_APPLIES_TO_TEXT, // Text: "Applies to: ..."
                PEARLANIUM_UPGRADE_INGREDIENTS_TEXT, // Text: "Ingredients: ..."
                PEARLANIUM_UPGRADE_TEXT, // Text: "Pearlanium Upgrade" (title)
                PEARLANIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, // Text for base slot hover
                PEARLANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, // Text for additions slot hover
                PEARLANIUM_UPGRADE_BASE_SLOT_TEXTURES, // Icons for empty base slots
                PEARLANIUM_UPGRADE_ADDITIONAL_SLOT_TEXTURES // Icons for empty addition slots
        ));




    public static final Item BRUTE_PEARLANIUM = register( "brute_pearlanium", new Item(new Item.Settings()));
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

    public static final ArmorItem PEARLANIUM_HELMET = register("pearlanium_helmet",
    new ArmorItem(ModArmorMaterial.PEARLANIUM, ArmorItem.Type.HELMET, new Item.Settings()
    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(41))));

    public static final ArmorItem PEARLANIUM_CHESTPLATE = register("pearlanium_chestplate",
    new ArmorItem(ModArmorMaterial.PEARLANIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings()
    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(41))));

    public static final ArmorItem PEARLANIUM_LEGGINGS = register("pearlanium_leggings",
    new ArmorItem(ModArmorMaterial.PEARLANIUM, ArmorItem.Type.LEGGINGS, new Item.Settings()
    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(41))));

    public static final ArmorItem PEARLANIUM_BOOTS = register("pearlanium_boots",
    new ArmorItem(ModArmorMaterial.PEARLANIUM, ArmorItem.Type.BOOTS, new Item.Settings()
    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(41))));


    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, PearlaniumMod.id(name), item);
    }

    public static void load() {
        
    }
}
