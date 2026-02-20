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
 * this mixin makes wardium armor super quiet when you walk
 * no noisy footsteps! the vibration suppressor handles the actual silence stuff
 * 
 * note: we don't mess with isSilent() anymore because it was breaking other sounds
 * the vibration suppressor does all the heavy lifting now
 */
@Mixin(value = Entity.class, priority = 2048)
public abstract class WardiumArmorMixin {

    /**
     * this makes you bypass the normal footstep effects
     * doesn't affect sounds, just the visual stepping stuff
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
