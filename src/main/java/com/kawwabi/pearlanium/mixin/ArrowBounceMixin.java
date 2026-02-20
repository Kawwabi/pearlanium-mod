package com.kawwabi.pearlanium.mixin;

import com.kawwabi.pearlanium.init.moditems;
import com.kawwabi.pearlanium.util.TeleportCancelTracker;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.util.List;
import java.util.UUID;

/**
 * this mixin watches arrows
 * when an arrow bounces off an enderman and hits the dumdum who shot it,
 * we give them the "yes today, i'm sorry" achievement
 * 
 * what needs to happen:
 * 1. player is wearing full pearlanium or wardium armor
 * 2. an enderman tried to teleport but got blocked
 * 3. an arrow bounced back and smacked the player within 3 seconds
 */
@Mixin(ArrowEntity.class)
public abstract class ArrowBounceMixin extends Entity {

    @Unique
    private boolean bouncedOffEnderman = false;
    @Unique
    private Vec3d previousPosition = null;

    public ArrowBounceMixin(EntityType<?> type, World world) {
        super(type, world);
    }

    /**
     * called when the arrow hits something - we check if it hit our own shooter lol
     */
    @Inject(at = @At("TAIL"), method = "onHit(Lnet/minecraft/entity/LivingEntity;)V")
    public void onHit(LivingEntity hitEntity, CallbackInfo callbackInfo) {
        if (this.getWorld().isClient) return;
        
        // did we just hit the person who shot us? :O
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        Entity owner = arrow.getOwner();
        
        if (owner instanceof PlayerEntity player && hitEntity == player) {
            // did we bounce off an enderman?
            if (this.bouncedOffEnderman) {
                // is the player wearing the fancy armor?
                if (isWearingFullPearlaniumOrWardiumArmor(player)) {
                    // was a teleport blocked recently? (within 3 seconds)
                    UUID playerUuid = player.getUuid();
                    if (TeleportCancelTracker.hasRecentTeleportCancel(playerUuid)) {
                        // okaay, everything lined up! here's your achievement 🎉
                        triggerYesTodayImSorryAchievement(player);
                        
                        // clean up so we don't give this again
                        TeleportCancelTracker.clearTeleportCancel(playerUuid);
                    }
                }
                // reset for next time
                this.bouncedOffEnderman = false;
            }
        }
    }

    /**
     * called every tick - we watch the arrow to see if it bounces off an enderman
     */
    @Inject(at = @At("TAIL"), method = "tick()V")
    public void tick(CallbackInfo callbackInfo) {
        if (this.getWorld().isClient) return;
        
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        Entity owner = arrow.getOwner();
        
        // only care about arrows players shot
        if (!(owner instanceof PlayerEntity)) return;
        
        Vec3d currentPos = this.getPos();
        
        // looking for nearby endermen to bounce off of
        if (!this.bouncedOffEnderman) {
            double detectionRange = 2.0;
            Box searchBox = this.getBoundingBox().expand(detectionRange);
            
            // finding endermen (including modded ones!)
            List<EndermanEntity> nearbyEndermen = this.getWorld().getEntitiesByClass(
                EndermanEntity.class,
                searchBox,
                enderman -> {
                    String typeId = enderman.getType().toString();
                    return typeId.endsWith("enderman") || typeId.endsWith("_enderman");
                }
            );
            
            if (!nearbyEndermen.isEmpty()) {
                if (previousPosition != null) {
                    Vec3d toOwner = owner.getPos().subtract(currentPos).normalize();
                    Vec3d velocity = this.getVelocity();
                    
                    // is it flying back toward the shooter?
                    if (velocity.length() > 0.1 && velocity.normalize().dotProduct(toOwner) > 0.5) {
                        this.bouncedOffEnderman = true;
                    }
                }
            }
        }
        
        // remember where we were for next frame
        this.previousPosition = currentPos;
    }

    /**
     * is the player wearing enough fancy armor? (4+ pieces)
     */
    @Unique
    private boolean isWearingFullPearlaniumOrWardiumArmor(PlayerEntity player) {
        return isWearingFullPearlaniumArmorSet(player) || isWearingFullWardiumArmorSet(player) || isWearingFullMixedArmorSet(player);
    }

    /**
     * checking if player has full pearlanium set
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
     * checking if player has full wardium set
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
     * mixed armor? we don't judge! as long as it's 4+ pieces from either set
     * like: pearlanium boots + wardium chestplate + wardium leggings + wardium helmet = works!
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
        
        // need at least 4 pieces total
        return (pearlaniumCount + wardiumCount) >= 4;
    }

    /**
     * give the player the achievement! 🏆
     */
    @Unique
    private void triggerYesTodayImSorryAchievement(PlayerEntity player) {
        if (!(player instanceof ServerPlayerEntity serverPlayer)) return;
        if (serverPlayer.getServer() == null) return;
        
        var advancementLoader = serverPlayer.getServer().getAdvancementLoader();
        net.minecraft.util.Identifier advancementId = net.minecraft.util.Identifier.of("pearlanium", "yes_today_im_sorry");
        net.minecraft.advancement.AdvancementEntry advancement = advancementLoader.get(advancementId);
        
        if (advancement != null) {
            serverPlayer.getAdvancementTracker().grantCriterion(advancement, "arrow_bounced_back");
        }
    }
}
