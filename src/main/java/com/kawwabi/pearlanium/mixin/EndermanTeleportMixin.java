package com.kawwabi.pearlanium.mixin;

import com.kawwabi.pearlanium.init.moditems;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

/**
 * Mixin to prevent Endermen from teleporting close to players wearing full Pearlanium or Wardium armor.
 * When the player is wearing a complete armor set from either set, Endermen cannot teleport to them.
 */
@Mixin(EndermanEntity.class)
public abstract class EndermanTeleportMixin {

    /**
     * Hook into the Enderman's teleport method to prevent teleportation 
     * to players wearing full Pearlanium or Wardium armor.
     */
    @Inject(at = @At("HEAD"), method = "teleportTo(DDD)Z", cancellable = true)
    public void onTeleportTo(double x, double y, double z, CallbackInfoReturnable<Boolean> callbackInfo) {
        // Check if there are any players nearby wearing full armor that should block teleportation
        if (shouldPreventTeleportation((EndermanEntity) (Object) this, x, y, z)) {
            playBlockedSound((EndermanEntity) (Object) this);
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
    }

    /**
     * Hook into the teleportRandomly method - used when Enderman is attacked/damaged.
     * This prevents the "teleport away when hit" behavior.
     */
    @Inject(at = @At("HEAD"), method = "teleportRandomly()Z", cancellable = true)
    public void onTeleportRandomly(CallbackInfoReturnable<Boolean> callbackInfo) {
        // Check if there are any players nearby wearing full armor that should block teleportation
        // Get the Enderman's current position as the target
        EndermanEntity enderman = (EndermanEntity) (Object) this;
        if (shouldPreventRandomTeleportation(enderman)) {
            playBlockedSound(enderman);
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
    }

    /**
     * Play a sound when teleport is blocked
     */
    @Unique
    private void playBlockedSound(EndermanEntity enderman) {
        enderman.getWorld().playSound(
            null, // no specific player - plays to all nearby players
            enderman.getX(),
            enderman.getY(),
            enderman.getZ(),
            SoundEvents.ENTITY_ENDER_EYE_DEATH,
            enderman.getSoundCategory(),
            0.15f, // volume (quieter)
            0.8f   // pitch
        );
    }

    /**
     * Check if random teleportation should be prevented based on nearby players' armor
     */
    @Unique
    private boolean shouldPreventRandomTeleportation(EndermanEntity enderman) {
        var world = enderman.getWorld();
        
        // Get the Enderman's current position
        net.minecraft.util.math.Vec3d endermanPos = enderman.getPos();
        
        // Check all players in the world
        for (PlayerEntity player : world.getPlayers()) {
            // Check if player is within 16 blocks (normal enderman teleport range)
            double distanceToEnderman = player.getPos().distanceTo(endermanPos);
            
            if (distanceToEnderman <= 16.0) {
                // Check if player is wearing full Pearlanium or Wardium armor
                if (isWearingFullPearlaniumArmorSet(player) || isWearingFullWardiumArmorSet(player)) {
                    triggerEnderShadowAdvancement(player);
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Check if teleportation should be prevented based on nearby players' armor
     */
    @Unique
    private boolean shouldPreventTeleportation(EndermanEntity enderman, double x, double y, double z) {
        // Get the enderman's world
        var world = enderman.getWorld();
        
        // Check all players in the world
        for (PlayerEntity player : world.getPlayers()) {
            // Check if player is within a reasonable distance of the teleport destination
            double distanceToTarget = player.getPos().distanceTo(new net.minecraft.util.math.Vec3d(x, y, z));
            
            // If player is within 8 blocks of where the enderman wants to teleport
            if (distanceToTarget <= 8.0) {
                // Check if player is wearing full Pearlanium or Wardium armor
                if (isWearingFullPearlaniumArmorSet(player) || isWearingFullWardiumArmorSet(player)) {
                    triggerEnderShadowAdvancement(player);
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * Trigger the ender_shadow advancement for a player
     */
    @Unique
    private void triggerEnderShadowAdvancement(PlayerEntity player) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
        if (serverPlayer.getServer() == null) return;
        
        var advancementLoader = serverPlayer.getServer().getAdvancementLoader();
        // Use "pearlanium" namespace to match the JSON file location
        net.minecraft.util.Identifier advancementId = net.minecraft.util.Identifier.of("pearlanium", "no_you_wont");
        net.minecraft.advancement.AdvancementEntry advancement = advancementLoader.get(advancementId);
        
        if (advancement != null) {
            serverPlayer.getAdvancementTracker().grantCriterion(advancement, "no_you_wont");
        }
    }

    /**
     * Check if a player is wearing the full Pearlanium armor set
     */
    @Unique
    private boolean isWearingFullPearlaniumArmorSet(PlayerEntity player) {
        ItemStack headItemStack = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItemStack = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsItemStack = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetItemStack = player.getEquippedStack(EquipmentSlot.FEET);

        return headItemStack.isOf(moditems.PEARLANIUM_HELMET)
                && chestItemStack.isOf(moditems.PEARLANIUM_CHESTPLATE)
                && legsItemStack.isOf(moditems.PEARLANIUM_LEGGINGS)
                && feetItemStack.isOf(moditems.PEARLANIUM_BOOTS);
    }

    /**
     * Check if a player is wearing the full Wardium armor set
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
