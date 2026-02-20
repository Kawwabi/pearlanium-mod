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

import com.kawwabi.pearlanium.util.TeleportCancelTracker;

/**
 * this mixin stops endermen from teleporting when you're wearing fancy armor
 */
@Mixin(EndermanEntity.class)
public abstract class EndermanTeleportMixin {

    /**
     * we intercept the teleport method - no escaping via portal-y stuff
     */
    @Inject(at = @At("HEAD"), method = "teleportTo(DDD)Z", cancellable = true)
    public void onTeleportTo(double x, double y, double z, CallbackInfoReturnable<Boolean> callbackInfo) {
        // check if there's a player nearby with fancy armor
        if (shouldPreventTeleportation((EndermanEntity) (Object) this, x, y, z)) {
            playBlockedSound((EndermanEntity) (Object) this);
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
    }

    /**
     * this stops them from teleporting away when you hit them
     * no running away! >:(
     */
    @Inject(at = @At("HEAD"), method = "teleportRandomly()Z", cancellable = true)
    public void onTeleportRandomly(CallbackInfoReturnable<Boolean> callbackInfo) {
        // check if someone's wearing the armor that blocks this
        EndermanEntity enderman = (EndermanEntity) (Object) this;
        if (shouldPreventRandomTeleportation(enderman)) {
            playBlockedSound(enderman);
            callbackInfo.setReturnValue(false);
            callbackInfo.cancel();
        }
    }

    /**
     * play a little "nope" sound when they can't teleport
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
     * checking if they tried to teleport away randomly (when hit)
     */
    @Unique
    private boolean shouldPreventRandomTeleportation(EndermanEntity enderman) {
        var world = enderman.getWorld();
        
        // enderman's current spot
        net.minecraft.util.math.Vec3d endermanPos = enderman.getPos();
        
        // check all players in the world
        for (PlayerEntity player : world.getPlayers()) {
            // are they close enough? (16 blocks is normal enderman range)
            double distanceToEnderman = player.getPos().distanceTo(endermanPos);
            
            if (distanceToEnderman <= 16.0) {
                // fancy armor check
                if (isWearingFullPearlaniumArmorSet(player) || isWearingFullWardiumArmorSet(player) || isWearingFullMixedArmorSet(player)) {
                    // remember this for the achievement later
                    TeleportCancelTracker.recordTeleportCancel(player.getUuid());
                    triggerEnderShadowAdvancement(player);
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * checking if they tried to teleport to a specific spot
     */
    @Unique
    private boolean shouldPreventTeleportation(EndermanEntity enderman, double x, double y, double z) {
        // enderman's world
        var world = enderman.getWorld();
        
        // check all players
        for (PlayerEntity player : world.getPlayers()) {
            // is player near where the enderman wants to go?
            double distanceToTarget = player.getPos().distanceTo(new net.minecraft.util.math.Vec3d(x, y, z));
            
            // if player is within 8 blocks
            if (distanceToTarget <= 8.0) {
                // do they have fancy armor?
                if (isWearingFullPearlaniumArmorSet(player) || isWearingFullWardiumArmorSet(player) || isWearingFullMixedArmorSet(player)) {
                    triggerEnderShadowAdvancement(player);
                    return true;
                }
            }
        }
        
        return false;
    }

    /**
     * give the "no you won't" achievement
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
     * checking for full pearlanium set
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
     * checking for full wardium set
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

    /**
     * mixed armor works too - just need 4+ pieces total
     * like: pearlanium boots + wardium chestplate + wardium leggings + wardium helmet = blocked!
     */
    @Unique
    private boolean isWearingFullMixedArmorSet(PlayerEntity player) {
        int pearlaniumCount = 0;
        int wardiumCount = 0;
        
        ItemStack headItemStack = player.getEquippedStack(EquipmentSlot.HEAD);
        ItemStack chestItemStack = player.getEquippedStack(EquipmentSlot.CHEST);
        ItemStack legsItemStack = player.getEquippedStack(EquipmentSlot.LEGS);
        ItemStack feetItemStack = player.getEquippedStack(EquipmentSlot.FEET);
        
        // count dem pearlanium pieces
        if (headItemStack.isOf(moditems.PEARLANIUM_HELMET)) pearlaniumCount++;
        if (chestItemStack.isOf(moditems.PEARLANIUM_CHESTPLATE)) pearlaniumCount++;
        if (legsItemStack.isOf(moditems.PEARLANIUM_LEGGINGS)) pearlaniumCount++;
        if (feetItemStack.isOf(moditems.PEARLANIUM_BOOTS)) pearlaniumCount++;
        
        // count dem wardium pieces
        if (headItemStack.isOf(moditems.WARDIUM_HELMET)) wardiumCount++;
        if (chestItemStack.isOf(moditems.WARDIUM_CHESTPLATE)) wardiumCount++;
        if (legsItemStack.isOf(moditems.WARDIUM_LEGGINGS)) wardiumCount++;
        if (feetItemStack.isOf(moditems.WARDIUM_BOOTS)) wardiumCount++;
        
        // need at least 4 pieces
        return (pearlaniumCount + wardiumCount) >= 4;
    }
}
