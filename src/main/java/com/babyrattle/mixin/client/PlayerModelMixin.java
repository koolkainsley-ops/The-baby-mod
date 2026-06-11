package com.babyrattle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.entity.model.PlayerEntityModel;
import net.minecraft.entity.LivingEntity;
import net.minecraft.util.math.Vec3f;

import com.babyrattle.status.BabyEffects;

@Mixin(PlayerEntityModel.class)
public class PlayerModelMixin {

    @Inject(method = "setAngles", at = @At("HEAD"))
    private void onSetAngles(LivingEntity entity, float limbSwing, float limbSwingAmount, float ageInTicks, float netHeadYaw, float headPitch, CallbackInfo ci) {
        if (BabyEffects.isBaby(entity)) {
            // Make the head 2 pixels bigger than the body when in baby mode
            PlayerEntityModel<?> model = (PlayerEntityModel<?>) (Object) this;
            
            // Scale the head up slightly
            model.head.scale(1.25f, 1.25f, 1.25f);
            
            // Scale the body down
            model.body.scale(0.8f, 0.8f, 0.8f);
            model.leftArm.scale(0.75f, 0.75f, 0.75f);
            model.rightArm.scale(0.75f, 0.75f, 0.75f);
            model.leftLeg.scale(0.75f, 0.75f, 0.75f);
            model.rightLeg.scale(0.75f, 0.75f, 0.75f);
        }
    }
}
