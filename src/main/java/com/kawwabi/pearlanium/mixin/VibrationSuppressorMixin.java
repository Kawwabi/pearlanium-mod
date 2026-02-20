package com.kawwabi.pearlanium.mixin;

import com.kawwabi.pearlanium.init.moditems;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.registry.entry.RegistryEntry;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.event.GameEvent;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

/**
 * this mixin makes wardium armor silent to sculk sensors
 * it stops sculk sensors from detecting the player when they're wearing full wardium
 * 
 * we let movement events (step, sneak) still go through
 * so the "avoid_vibration" achievement can trigger, but the sensor doesn't "hear" them
 * 
 * how it works:
 * 1. only applies when player is wearing full wardium armor
 * 2. movement stuff (step, sneak) → let through (for achievement)
 * 3. everything else → SILENCE >:D
 */
@Mixin(ServerWorld.class)
public abstract class VibrationSuppressorMixin {

    /**
     * movement events we want to let through for the achievement
     */
    @Unique
    private static final java.util.Set<String> MOVEMENT_EVENTS = java.util.Set.of(
        "step",
        "sneak"
    );

    /**
     * this is where game events get emitted, we intercept them here!
     */
    @Inject(at = @At("HEAD"), method = "emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/event/GameEvent$Emitter;)V", cancellable = true)
    private void onEmitGameEvent(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter, CallbackInfo callbackInfo) {
        if (shouldSuppressVibration(event, emitterPos, emitter)) {
            callbackInfo.cancel();
        }
    }

    /**
     * should we silence this event?
     * only if it's a player wearing full wardium armor
     */
    @Unique
    @SuppressWarnings("resource") // ServerWorld is not closeable - false positive from Java language server
    private boolean shouldSuppressVibration(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter) {
        // wait until world is ready
        ServerWorld thisWorld = (ServerWorld)(Object)this;
        if (thisWorld.getTime() <= 0) {
            return false;
        }
        
        Entity sourceEntity = emitter.sourceEntity();
        
        // only care about players in wardium armor
        if (sourceEntity instanceof PlayerEntity sourcePlayer) {
            if (isWearingFullWardiumArmorSet(sourcePlayer)) {
                // let movement events through for achievement stuff
                if (isMovementEvent(event)) {
                    return false; // don't silence - let it happen
                }
                // everything else? SILENCE IT
                return true;
            }
        }
        
        // not our problem otherwise
        return false;
    }

    /**
     * is this a movement-related event?
     */
    @Unique
    private boolean isMovementEvent(RegistryEntry<GameEvent> event) {
        // get event name and check if it's movement-y
        String eventName = event.toString();
        for (String movement : MOVEMENT_EVENTS) {
            if (eventName.contains(movement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * check if player has full wardium set
     */
    @Unique
    private boolean isWearingFullWardiumArmorSet(PlayerEntity player) {
        ItemStack headItemStack = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItemStack = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsItemStack = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetItemStack = player.getEquippedStack(EquipmentSlot.FEET);

        return headItemStack.isOf(moditems.WARDIUM_HELMET)
                && chestItemStack.isOf(moditems.WARDIUM_CHESTPLATE)
                && legsItemStack.isOf(moditems.WARDIUM_LEGGINGS)
                && feetItemStack.isOf(moditems.WARDIUM_BOOTS);
    }
}
