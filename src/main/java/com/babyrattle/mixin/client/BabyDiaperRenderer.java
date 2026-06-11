package com.babyrattle.mixin.client;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRenderer;
import net.minecraft.client.render.entity.LivingEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;

import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
@Mixin(LivingEntityRenderer.class)
public class BabyDiaperRenderer {

    @Inject(method = "render(Lnet/minecraft/entity/LivingEntity;FFLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V", 
             at = @At("RETURN"))
    private void onRender(LivingEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, CallbackInfo ci) {
        if (entity instanceof PlayerEntity && BabyEffects.isBaby(entity)) {
            // Render diaper overlay on the baby player
            matrices.push();
            
            // Position diaper on lower body
            matrices.translate(0, -0.3f, 0);
            
            // Simple diaper rendering (white color)
            DiaperRenderHelper.renderDiaper(matrices, vertexConsumers, light);
            
            matrices.pop();
        }
    }
}
