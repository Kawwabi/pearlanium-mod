package com.kawwabi.pearlanium.mixin;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.mob.EndermanEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ArrowEntity;
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

/**
 * Mixin to detect when an arrow bounces off an Enderman and hits the original shooter.
 * This triggers the "Yes today, I'm sorry." achievement.
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
     * Hook into the arrow's onHit method to detect when it hits the player.
     * If it hits the original owner AND has bounced off an Enderman, trigger achievement.
     */
    @Inject(at = @At("TAIL"), method = "onHit(Lnet/minecraft/entity/LivingEntity;)V")
    public void onHit(LivingEntity hitEntity, CallbackInfo callbackInfo) {
        if (this.getWorld().isClient) return;
        
        // Check if the arrow hit its original owner (the player who shot it)
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        Entity owner = arrow.getOwner();
        
        if (owner instanceof PlayerEntity player && hitEntity == player) {
            // Only trigger if the arrow has bounced off an Enderman
            if (this.bouncedOffEnderman) {
                triggerYesTodayImSorryAchievement(player);
                this.bouncedOffEnderman = false;
            }
        }
    }

    /**
     * Hook into the arrow's tick method to detect bouncing and check if it damages the original shooter.
     */
    @Inject(at = @At("TAIL"), method = "tick()V")
    public void tick(CallbackInfo callbackInfo) {
        if (this.getWorld().isClient) return;
        
        ArrowEntity arrow = (ArrowEntity) (Object) this;
        Entity owner = arrow.getOwner();
        
        // Only track arrows fired by players
        if (!(owner instanceof PlayerEntity)) return;
        
        Vec3d currentPos = this.getPos();
        
        // If we haven't bounced off an Enderman yet, check for nearby Endermen
        if (!this.bouncedOffEnderman) {
            double detectionRange = 2.0;
            Box searchBox = this.getBoundingBox().expand(detectionRange);
            
            List<EndermanEntity> nearbyEndermen = this.getWorld().getEntitiesByType(
                net.minecraft.entity.EntityType.ENDERMAN,
                searchBox,
                enderman -> true
            );
            
            if (!nearbyEndermen.isEmpty()) {
                if (previousPosition != null) {
                    Vec3d toOwner = owner.getPos().subtract(currentPos).normalize();
                    Vec3d velocity = this.getVelocity();
                    
                    // If velocity is pointing toward the owner, it bounced back
                    if (velocity.length() > 0.1 && velocity.normalize().dotProduct(toOwner) > 0.5) {
                        this.bouncedOffEnderman = true;
                    }
                }
            }
        }
        
        // Update previous position for next frame
        this.previousPosition = currentPos;
    }

    /**
     * Trigger the "Yes today, I'm sorry." achievement for the player.
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
