package com.babyrattle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.BipedEntityModel;
import net.minecraft.entity.LivingEntity;

import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
@Mixin(BipedEntityModel.class)
public class BabyHeadScaleMixin {

    @Shadow public ModelPart head;
    @Shadow public ModelPart body;
    @Shadow public ModelPart leftArm;
    @Shadow public ModelPart rightArm;
    @Shadow public ModelPart leftLeg;
    @Shadow public ModelPart rightLeg;

    @Inject(method = "setAngles", at = @At("TAIL"))
    private void adjustBabyScale(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (BabyEffects.isBaby(entity)) {
            // Head 2 pixels (0.125 block units) bigger than normal
            this.head.scaleX = 1.25f;
            this.head.scaleY = 1.25f;
            this.head.scaleZ = 1.25f;
            
            // Scale down body proportionally
            this.body.scaleX = 0.8f;
            this.body.scaleY = 0.8f;
            this.body.scaleZ = 0.8f;
            
            this.leftArm.scaleX = 0.75f;
            this.leftArm.scaleY = 0.75f;
            this.leftArm.scaleZ = 0.75f;
            
            this.rightArm.scaleX = 0.75f;
            this.rightArm.scaleY = 0.75f;
            this.rightArm.scaleZ = 0.75f;
            
            this.leftLeg.scaleX = 0.75f;
            this.leftLeg.scaleY = 0.75f;
            this.leftLeg.scaleZ = 0.75f;
            
            this.rightLeg.scaleX = 0.75f;
            this.rightLeg.scaleY = 0.75f;
            this.rightLeg.scaleZ = 0.75f;
        }
    }
}
