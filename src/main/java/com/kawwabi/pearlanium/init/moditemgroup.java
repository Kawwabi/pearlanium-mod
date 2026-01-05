package com.kawwabi.pearlanium.init;

import com.kawwabi.pearlanium.PearlaniumMod;
import net.fabricmc.fabric.api.itemgroup.v1.FabricItemGroup;
import net.minecraft.item.ItemGroup;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.text.Text;

import java.util.Optional;

public class moditemgroup {
    public static final Text PEARLANIUM_TITLE = Text.translatable("itemGroup." + PearlaniumMod.MOD_ID + ".pearlanium_group");

    public static final ItemGroup PEARLANIUM_GROUP = register("pearlanium_group", FabricItemGroup.builder()
            .displayName(PEARLANIUM_TITLE)
            .icon(moditems.BRUTE_PEARLANIUM::getDefaultStack)
            .entries((displayContext, entries) -> Registries.ITEM.getIds()
                    .stream()
                    .filter(key -> key.getNamespace().equals(PearlaniumMod.MOD_ID))
                    .map(Registries.ITEM::getOrEmpty)
                    .map(Optional::orElseThrow)
                    .forEach(entries::add))
            .build());

    public static <T extends ItemGroup> T register(String name, T itemGroup) {
        return Registry.register(Registries.ITEM_GROUP, PearlaniumMod.id(name), itemGroup);
    }

    public static void load() {}
}