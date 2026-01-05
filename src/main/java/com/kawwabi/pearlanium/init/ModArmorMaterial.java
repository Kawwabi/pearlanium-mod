package com.kawwabi.pearlanium.init;

import com.kawwabi.pearlanium.PearlaniumMod;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;

import java.util.List;
import java.util.Map;
import java.util.function.Supplier;

public class ModArmorMaterial {
    public static final RegistryEntry<ArmorMaterial> PEARLANIUM = register("pearlanium", Map.of(
                    ArmorItem.Type.HELMET, 4,
                    ArmorItem.Type.CHESTPLATE, 9,
                    ArmorItem.Type.LEGGINGS, 7,
                    ArmorItem.Type.BOOTS, 4
            ),
            22,
            SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
            () -> Ingredient.ofItems(moditems.PEARLANIUM_INGOT),
            3.5F,
            0.1F,
            false);

        public static final RegistryEntry<ArmorMaterial> REINFORCED_PEARLANIUM = register("reinforced_pearlanium", Map.of(
                ArmorItem.Type.HELMET, 5,
                ArmorItem.Type.CHESTPLATE, 10,
                ArmorItem.Type.LEGGINGS, 8,
                ArmorItem.Type.BOOTS, 5
        ),
                22,
                SoundEvents.ITEM_ARMOR_EQUIP_DIAMOND,
                () -> Ingredient.ofItems(Items.ECHO_SHARD),
                4,
                0.2F,
                false);

    public static RegistryEntry<ArmorMaterial> register(String id, Map<ArmorItem.Type, Integer> defensePoints,
                                                        int enchantability, RegistryEntry<SoundEvent> equipSound,
                                                        Supplier<Ingredient> repairIngredient, float toughness,
                                                        float knockbackResistance, boolean dyeable) {
        List<ArmorMaterial.Layer> layers = List.of(
                new ArmorMaterial.Layer(PearlaniumMod.id(id), "", dyeable)
        );

        var material = new ArmorMaterial(defensePoints, enchantability, equipSound, repairIngredient, layers,
                toughness, knockbackResistance);
        material = Registry.register(Registries.ARMOR_MATERIAL, PearlaniumMod.id(id), material);

        return RegistryEntry.of(material);
    }

    public static void load() {}
}
