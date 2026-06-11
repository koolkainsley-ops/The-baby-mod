package com.babyrattle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
@Mixin(PlayerEntityModel.class)
public class BabyCrawlingMixin {

    @Shadow public ModelPart head;
    @Shadow public ModelPart body;
    @Shadow public ModelPart leftArm;
    @Shadow public ModelPart rightArm;
    @Shadow public ModelPart leftLeg;
    @Shadow public ModelPart rightLeg;

    @Inject(method = "setAngles", at = @At("TAIL"))
    private void adjustBabyCrawling(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (entity instanceof PlayerEntity && BabyEffects.isBaby(entity)) {
            PlayerEntity player = (PlayerEntity) entity;
            
            // Check if player is moving
            double velX = player.getVelocity().x;
            double velZ = player.getVelocity().z;
            double speed = Math.sqrt(velX * velX + velZ * velZ);
            boolean isMoving = speed > 0.01;
            
            if (isMoving) {
                // Crawling animation
                applyCrawlingPose(ageInTicks, limbSwing, limbSwingAmount);
            } else {
                // Sitting idle animation
                applySittingPose();
            }
        }
    }

    private void applyCrawlingPose(float ageInTicks, float limbSwing, float limbSwingAmount) {
        // Lower the head and body for crawling position
        this.head.pitch = (float) Math.toRadians(30);
        this.body.pitch = (float) Math.toRadians(45);
        this.body.pivotY = 12f;
        
        // Crawling arm animation
        this.leftArm.pitch = (float) Math.sin(limbSwing * 0.6662f + (float) Math.PI) * 2.0f * limbSwingAmount * 0.5f;
        this.leftArm.roll = 0;
        this.rightArm.pitch = (float) Math.sin(limbSwing * 0.6662f) * 2.0f * limbSwingAmount * 0.5f;
        this.rightArm.roll = 0;
        
        // Crawling leg animation
        this.leftLeg.pitch = (float) Math.sin(limbSwing * 0.6662f) * 1.4f * limbSwingAmount;
        this.rightLeg.pitch = (float) Math.sin(limbSwing * 0.6662f + (float) Math.PI) * 1.4f * limbSwingAmount;
    }

    private void applySittingPose() {
        // Sitting position
        this.head.pitch = (float) Math.toRadians(0);
        this.body.pitch = (float) Math.toRadians(20);
        this.body.pivotY = 14f; // Raise body slightly for sitting
        
        // Arms in sitting position
        this.leftArm.pitch = (float) Math.toRadians(80);
        this.leftArm.roll = (float) Math.toRadians(20);
        this.rightArm.pitch = (float) Math.toRadians(80);
        this.rightArm.roll = (float) Math.toRadians(-20);
        
        // Legs in sitting position
        this.leftLeg.pitch = (float) Math.toRadians(100);
        this.rightLeg.pitch = (float) Math.toRadians(100);
    }
}
