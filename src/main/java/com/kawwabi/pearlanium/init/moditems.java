package com.kawwabi.pearlanium.init;


import com.kawwabi.pearlanium.PearlaniumMod;
import com.kawwabi.pearlanium.list.enums.PearlaniumModToolMaterials;

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

    // text stuff for the pearlanium upgrade smithing template tooltip
    private static final Text PEARLANIUM_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", PearlaniumMod.id("pearlanium_upgrade"))).formatted(Formatting.GRAY);
    private static final Text PEARLANIUM_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.applies_to"))).formatted(Formatting.BLUE);
    private static final Text PEARLANIUM_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.ingredients"))).formatted(Formatting.BLUE);
    private static final Text PEARLANIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.base_slot_description")));
    private static final Text PEARLANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.pearlanium_upgrade.additions_slot_description")));

    // empty slot textures for the template UI
    private static final Identifier EMPTY_SLOT_HELMET_TEXTURE = Identifier.of("item/empty_armor_slot_helmet");
    private static final Identifier EMPTY_SLOT_CHESTPLATE_TEXTURE = Identifier.of("item/empty_armor_slot_chestplate");
    private static final Identifier EMPTY_SLOT_LEGGINGS_TEXTURE = Identifier.of("item/empty_armor_slot_leggings");
    private static final Identifier EMPTY_SLOT_BOOTS_TEXTURE = Identifier.of("item/empty_armor_slot_boots");
    private static final Identifier EMPTY_SLOT_HOE_TEXTURE = Identifier.of("item/empty_slot_hoe");
    private static final Identifier EMPTY_SLOT_AXE_TEXTURE = Identifier.of("item/empty_slot_axe");
    private static final Identifier EMPTY_SLOT_SWORD_TEXTURE = Identifier.of("item/empty_slot_sword");
    private static final Identifier EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.of("item/empty_slot_shovel");
    private static final Identifier EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.of("item/empty_slot_pickaxe");
    private static final Identifier EMPTY_SLOT_INGOT_TEXTURE = Identifier.of("item/empty_slot_ingot");

    // which slots show up in the template
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
            EMPTY_SLOT_INGOT_TEXTURE // the slot where you put the ingot
    );

    public static final Item PEARLANIUM_UPGRADE_SMITHING_TEMPLATE = register( "pearlanium_upgrade_smithing_template",
        new SmithingTemplateItem(
                PEARLANIUM_UPGRADE_APPLIES_TO_TEXT, // "applies to: ..."
                PEARLANIUM_UPGRADE_INGREDIENTS_TEXT, // "ingredients: ..."
                PEARLANIUM_UPGRADE_TEXT, // "pearlanium upgrade" (title)
                PEARLANIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, // text when hovering base slot
                PEARLANIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, // text when hovering additions slot
                PEARLANIUM_UPGRADE_BASE_SLOT_TEXTURES, // icons for empty base slots
                PEARLANIUM_UPGRADE_ADDITIONAL_SLOT_TEXTURES // icons for empty addition slots
        ));


    // same thing but for wardium
    private static final Text WARDIUM_UPGRADE_TEXT = Text.translatable(Util.createTranslationKey("upgrade", PearlaniumMod.id("wardium_upgrade"))).formatted(Formatting.GRAY);
    private static final Text WARDIUM_UPGRADE_APPLIES_TO_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.wardium_upgrade.applies_to"))).formatted(Formatting.BLUE);
    private static final Text WARDIUM_UPGRADE_INGREDIENTS_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.wardium_upgrade.ingredients"))).formatted(Formatting.BLUE);
    private static final Text WARDIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.wardium_upgrade.base_slot_description")));
    private static final Text WARDIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT = Text.translatable(Util.createTranslationKey("item", PearlaniumMod.id("smithing_template.wardium_upgrade.additions_slot_description")));

    private static final Identifier WARDIUM_EMPTY_SLOT_HELMET_TEXTURE = Identifier.of("item/empty_armor_slot_helmet");
    private static final Identifier WARDIUM_EMPTY_SLOT_CHESTPLATE_TEXTURE = Identifier.of("item/empty_armor_slot_chestplate");
    private static final Identifier WARDIUM_EMPTY_SLOT_LEGGINGS_TEXTURE = Identifier.of("item/empty_armor_slot_leggings");
    private static final Identifier WARDIUM_EMPTY_SLOT_BOOTS_TEXTURE = Identifier.of("item/empty_armor_slot_boots");
    private static final Identifier WARDIUM_EMPTY_SLOT_HOE_TEXTURE = Identifier.of("item/empty_slot_hoe");
    private static final Identifier WARDIUM_EMPTY_SLOT_AXE_TEXTURE = Identifier.of("item/empty_slot_axe");
    private static final Identifier WARDIUM_EMPTY_SLOT_SWORD_TEXTURE = Identifier.of("item/empty_slot_sword");
    private static final Identifier WARDIUM_EMPTY_SLOT_SHOVEL_TEXTURE = Identifier.of("item/empty_slot_shovel");
    private static final Identifier WARDIUM_EMPTY_SLOT_PICKAXE_TEXTURE = Identifier.of("item/empty_slot_pickaxe");
    private static final Identifier WARDIUM_EMPTY_SLOT_INGOT_TEXTURE = Identifier.of("item/empty_slot_ingot");

    private static final List<Identifier> WARDIUM_UPGRADE_BASE_SLOT_TEXTURES = List.of(
            WARDIUM_EMPTY_SLOT_HELMET_TEXTURE,
            WARDIUM_EMPTY_SLOT_SWORD_TEXTURE,
            WARDIUM_EMPTY_SLOT_CHESTPLATE_TEXTURE,
            WARDIUM_EMPTY_SLOT_PICKAXE_TEXTURE,
            WARDIUM_EMPTY_SLOT_LEGGINGS_TEXTURE,
            WARDIUM_EMPTY_SLOT_AXE_TEXTURE,
            WARDIUM_EMPTY_SLOT_BOOTS_TEXTURE,
            WARDIUM_EMPTY_SLOT_HOE_TEXTURE,
            WARDIUM_EMPTY_SLOT_SHOVEL_TEXTURE
    );
    private static final List<Identifier> WARDIUM_UPGRADE_ADDITIONAL_SLOT_TEXTURES = List.of(
            WARDIUM_EMPTY_SLOT_INGOT_TEXTURE // where the ingot goes
    );

    public static final Item WARDIUM_UPGRADE_SMITHING_TEMPLATE = register( "wardium_upgrade_smithing_template",
            new SmithingTemplateItem(
                    WARDIUM_UPGRADE_APPLIES_TO_TEXT, // "applies to: ..."
                    WARDIUM_UPGRADE_INGREDIENTS_TEXT, // "ingredients: ..."
                    WARDIUM_UPGRADE_TEXT, // "wardium upgrade" (title)
                    WARDIUM_UPGRADE_BASE_SLOT_DESCRIPTION_TEXT, // text when hovering base slot
                    WARDIUM_UPGRADE_ADDITIONS_SLOT_DESCRIPTION_TEXT, // text when hovering additions slot
                    WARDIUM_UPGRADE_BASE_SLOT_TEXTURES, // icons for empty base slots
                    WARDIUM_UPGRADE_ADDITIONAL_SLOT_TEXTURES // icons for empty addition slots
            ));

    // Items
    public static final Item BRUTE_PEARLANIUM = register( "brute_pearlanium", new Item(new Item.Settings()));
    public static final Item PEARLANIUM_INGOT = register( "pearlanium_ingot", new Item(new Item.Settings()));
    public static final Item CALIBRATED_ECHO_SHARD = register( "calibrated_echo_shard", new Item(new Item.Settings()));

    // Pearlanium Tools
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

    // Wardium Tools
    public static final SwordItem WARDIUM_SWORD = register("wardium_sword",
    new SwordItem(PearlaniumModToolMaterials.WARDIUM, new Item.Settings()
    .attributeModifiers(SwordItem.createAttributeModifiers(PearlaniumModToolMaterials.WARDIUM, 3, -2.4f))));

    public static final PickaxeItem WARDIUM_PICKAXE = register("wardium_pickaxe",
    new PickaxeItem(PearlaniumModToolMaterials.WARDIUM, new Item.Settings()
    .attributeModifiers(PickaxeItem.createAttributeModifiers(PearlaniumModToolMaterials.WARDIUM, 1, -2.8F))));

    public static final AxeItem WARDIUM_AXE = register("wardium_axe",
    new AxeItem(PearlaniumModToolMaterials.WARDIUM, new Item.Settings()
    .attributeModifiers(AxeItem.createAttributeModifiers(PearlaniumModToolMaterials.WARDIUM, 5, -3.0F))));

    public static final ShovelItem WARDIUM_SHOVEL = register("wardium_shovel",
    new ShovelItem(PearlaniumModToolMaterials.WARDIUM, new Item.Settings()
    .attributeModifiers(ShovelItem.createAttributeModifiers(PearlaniumModToolMaterials.WARDIUM, 1.5F, -3.0F))));

    public static final HoeItem WARDIUM_HOE = register("wardium_hoe",
    new HoeItem(PearlaniumModToolMaterials.WARDIUM, new Item.Settings()
    .attributeModifiers(HoeItem.createAttributeModifiers(PearlaniumModToolMaterials.WARDIUM, 0, -3.0F))));

    // Pearlanium Armor
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

    // Wardium Armor
    public static final ArmorItem WARDIUM_HELMET = register("wardium_helmet",
    new ArmorItem(ModArmorMaterial.WARDIUM, ArmorItem.Type.HELMET, new Item.Settings()
    .maxDamage(ArmorItem.Type.HELMET.getMaxDamage(41))));

    public static final ArmorItem WARDIUM_CHESTPLATE = register("wardium_chestplate",
    new ArmorItem(ModArmorMaterial.WARDIUM, ArmorItem.Type.CHESTPLATE, new Item.Settings()
    .maxDamage(ArmorItem.Type.CHESTPLATE.getMaxDamage(41))));

    public static final ArmorItem WARDIUM_LEGGINGS = register("wardium_leggings",
    new ArmorItem(ModArmorMaterial.WARDIUM, ArmorItem.Type.LEGGINGS, new Item.Settings()
    .maxDamage(ArmorItem.Type.LEGGINGS.getMaxDamage(41))));

    public static final ArmorItem WARDIUM_BOOTS = register("wardium_boots",
    new ArmorItem(ModArmorMaterial.WARDIUM, ArmorItem.Type.BOOTS, new Item.Settings()
    .maxDamage(ArmorItem.Type.BOOTS.getMaxDamage(41))));



    public static <T extends Item> T register(String name, T item) {
        return Registry.register(Registries.ITEM, PearlaniumMod.id(name), item);
    }

    public static void load() {
        
    }
}
