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
 * Mixin to suppress game events (vibrations) when the source entity is a player wearing full Wardium armor.
 * This prevents sculk sensors from detecting sounds/vibrations caused by the player.
 * 
 * Key insight: We allow the player's movement events (step, sneak) to pass through so the
 * "avoid_vibration" advancement can trigger. The WardiumArmorMixin's isSilent() check makes
 * these events register as "silent" to nearby sensors.
 * 
 * Suppression logic:
 * 1. Only suppress when the SOURCE entity is a player wearing full Wardium armor
 * 2. Movement events (step, sneak) → ALLOW (for advancement)
 * 3. Other events → SUPPRESS
 */
@Mixin(ServerWorld.class)
public abstract class VibrationSuppressorMixin {

    /**
     * Movement-related game events that should pass through (for advancement)
     */
    @Unique
    private static final java.util.Set<String> MOVEMENT_EVENTS = java.util.Set.of(
        "step",
        "sneak"
    );

    /**
     * Hook into the emitGameEvent method.
     * This is where game events are emitted and vibrations are created.
     */
    @Inject(at = @At("HEAD"), method = "emitGameEvent(Lnet/minecraft/registry/entry/RegistryEntry;Lnet/minecraft/util/math/Vec3d;Lnet/minecraft/world/event/GameEvent$Emitter;)V", cancellable = true)
    private void onEmitGameEvent(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter, CallbackInfo callbackInfo) {
        if (shouldSuppressVibration(event, emitterPos, emitter)) {
            callbackInfo.cancel();
        }
    }

    /**
     * Check if the game event should be suppressed.
     * Only suppress when the source entity is a player wearing full Wardium armor.
     */
    @Unique
    @SuppressWarnings("resource") // ServerWorld is not closeable - false positive from Java language server
    private boolean shouldSuppressVibration(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter) {
        // Only suppress if the world is fully loaded and ready
        // Check that the world has started (time > 0 indicates initial loading is complete)
        ServerWorld thisWorld = (ServerWorld)(Object)this;
        if (thisWorld.getTime() <= 0) {
            return false;
        }
        
        Entity sourceEntity = emitter.sourceEntity();
        
        // Only suppress when the SOURCE entity is an armored player
        if (sourceEntity instanceof PlayerEntity sourcePlayer) {
            if (isWearingFullWardiumArmorSet(sourcePlayer)) {
                // Allow movement events (step, sneak) to pass through for advancement
                // The isSilent() check in WardiumArmorMixin makes these "silent"
                if (isMovementEvent(event)) {
                    return false; // Don't suppress - let it through
                }
                // Suppress all other events when player is wearing full Wardium
                return true;
            }
        }
        
        // Don't suppress anything else - no radius-based suppression
        return false;
    }

    /**
     * Check if the event is a movement-related event
     */
    @Unique
    private boolean isMovementEvent(RegistryEntry<GameEvent> event) {
        // Get event name from registry entry - use toString() for simple name
        String eventName = event.toString();
        for (String movement : MOVEMENT_EVENTS) {
            if (eventName.contains(movement)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Check if a player is wearing the full Wardium armor set.
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
