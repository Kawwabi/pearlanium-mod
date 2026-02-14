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
 * 1. When player IS THE SOURCE and event is movement-related (step, sneak) → ALLOW (for advancement)
 * 2. When player IS THE SOURCE and event is block-related (place, break, hit) → SUPPRESS
 * 3. When events occur near armored player (within 5 blocks) → SUPPRESS
 */
@Mixin(ServerWorld.class)
public abstract class VibrationSuppressorMixin {

    /**
     * Radius in blocks for suppressing nearby vibrations
     */
    @Unique
    private static final double SUPPRESSION_RADIUS = 5.0;

    /**
     * Movement-related game events that should pass through (for advancement)
     */
    @Unique
    private static final java.util.Set<String> MOVEMENT_EVENTS = java.util.Set.of(
        "step",
        "sneak"
    );

    /**
     * Block interaction events that should be suppressed even when player is source
     */
    @Unique
    private static final java.util.Set<String> BLOCK_EVENTS = java.util.Set.of(
        "block_place",
        "block_break",
        "hit_attack",
        "hit_damage"
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
     */
    @Unique
    private boolean shouldSuppressVibration(RegistryEntry<GameEvent> event, Vec3d emitterPos, GameEvent.Emitter emitter) {
        Entity sourceEntity = emitter.sourceEntity();
        
        // Check if source entity is an armored player
        if (sourceEntity instanceof PlayerEntity sourcePlayer) {
            if (isWearingFullWardiumArmorSet(sourcePlayer)) {
                // Allow movement events (step, sneak) to pass through for advancement
                // The isSilent() check in WardiumArmorMixin makes these "silent"
                if (isMovementEvent(event)) {
                    return false; // Don't suppress - let it through
                }
                // Suppress block interaction events
                return true;
            }
        }
        
        // Check if event is near an armored player (radius suppression)
        return isNearArmoredPlayer(emitterPos);
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
     * Check if any player wearing full Wardium armor is within the suppression radius
     */
    @SuppressWarnings("resource") // 'world' is just a typed reference to 'this', not an actual closeable resource
    @Unique
    private boolean isNearArmoredPlayer(Vec3d pos) {
        ServerWorld world = (ServerWorld) (Object) this;
        
        for (PlayerEntity player : world.getPlayers()) {
            if (isWearingFullWardiumArmorSet(player)) {
                double distance = player.getPos().distanceTo(pos);
                if (distance <= SUPPRESSION_RADIUS) {
                    return true;
                }
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
