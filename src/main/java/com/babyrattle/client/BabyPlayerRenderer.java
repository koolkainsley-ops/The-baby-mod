package com.babyrattle.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.model.ModelPart;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.render.entity.EntityRendererFactory;
import net.minecraft.client.render.entity.PlayerEntityRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.util.math.Vec3f;

import com.babyrattle.status.BabyEffects;

@Environment(EnvType.CLIENT)
public class BabyPlayerRenderer extends PlayerEntityRenderer {

    public BabyPlayerRenderer(EntityRendererFactory.Context context) {
        super(context, false);
    }

    @Override
    public void render(PlayerEntity entity, float yaw, float tickDelta, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        if (BabyEffects.isBaby(entity)) {
            // Scale down the player to baby size
            matrices.push();
            matrices.scale(0.5f, 0.5f, 0.5f);
            
            // Adjust position
            matrices.translate(0, 0.5f, 0);
        }

        super.render(entity, yaw, tickDelta, matrices, vertexConsumers, light);

        if (BabyEffects.isBaby(entity)) {
            matrices.pop();
            
            // Render pacifier
            matrices.push();
            renderPacifier(entity, matrices, vertexConsumers, light);
            matrices.pop();
        }
    }

    private void renderPacifier(PlayerEntity player, MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        matrices.push();
        
        // Position pacifier at baby's mouth
        matrices.translate(player.getX(), player.getY() + 0.5f, player.getZ());
        matrices.multiply(player.getRotationQuaternion());
        matrices.translate(0.1f, 0.15f, -0.3f);
        
        // Render pacifier using a simple textured model
        PacifierModel.render(matrices, vertexConsumers, light, 0xFFFFFFFF);
        
        matrices.pop();
    }
}
