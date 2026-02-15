package com.kawwabi.pearlanium.mixin;

import com.kawwabi.pearlanium.init.moditems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to prevent players wearing full wardium armor from triggering skulk sensors.
 * The bypassesSteppingEffects is kept to skip the regular stepping animation/footstep logic
 * while the VibrationSuppressorMixin handles suppressing the actual vibration events.
 * 
 * Note: We no longer override isSilent() as it was breaking other sounds like armor equip sounds.
 * The skulk sensor suppression is handled entirely by VibrationSuppressorMixin now.
 */
@Mixin(value = Entity.class, priority = 2048)
public abstract class WardiumArmorMixin {

    /**
     * This makes the player bypass regular stepping effects - this is what makes
     * Wardium armor "quiet" when walking. It doesn't affect audio, just the 
     * stepping animation/particle effects.
     */
    @Inject(at = @At("HEAD"), method = "bypassesSteppingEffects", cancellable = true)
    public void bypassesSteppingEffects(CallbackInfoReturnable<Boolean> callbackInfoReturnable) {
        if (isWearingFullWardiumArmorSet()) {
            callbackInfoReturnable.setReturnValue(true);
            callbackInfoReturnable.cancel();
        }
    }

    @Unique
    private boolean isWearingFullWardiumArmorSet() {
        Entity self = (Entity) (Object) this;
        
        if (!(self instanceof LivingEntity livingEntity)) {
            return false;
        }

        ItemStack headItemStack = livingEntity.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItemStack = livingEntity.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsItemStack = livingEntity.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetItemStack = livingEntity.getEquippedStack(EquipmentSlot.FEET);

        return headItemStack.isOf(moditems.WARDIUM_HELMET)
                && chestItemStack.isOf(moditems.WARDIUM_CHESTPLATE)
                && legsItemStack.isOf(moditems.WARDIUM_LEGGINGS)
                && feetItemStack.isOf(moditems.WARDIUM_BOOTS);
    }
}
