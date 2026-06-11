package com.babyrattle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class BabyCrawlHeightMixin {

    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", 
             at = @At("HEAD"))
    private void adjustCrawlHeight(LivingEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof PlayerEntity && BabyEffects.isBaby(entity)) {
            PlayerEntity player = (PlayerEntity) entity;
            
            // Check if player is moving
            double velX = player.getVelocity().x;
            double velZ = player.getVelocity().z;
            double speed = Math.sqrt(velX * velX + velZ * velZ);
            
            if (speed > 0.01) {
                // Lower camera/rendering when crawling
                matrices.translate(0, 0.4f, 0);
            }
        }
    }
}
