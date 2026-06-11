package com.babyrattle.mixin.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class DiaperRenderHelper {

    public static void renderDiaper(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntityCutout(getTextureId()));
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        
        // Render diaper as a simple white fabric-like shape
        int diaperColor = 0xFFFFFFFF; // White color
        
        // Front panel
        renderPanel(vertexConsumer, positionMatrix, light, diaperColor, -0.3f, 0, -0.15f, 0.6f, 0.3f);
        
        // Back panel
        renderPanel(vertexConsumer, positionMatrix, light, diaperColor, -0.3f, 0, 0.15f, 0.6f, 0.3f);
    }

    private static void renderPanel(VertexConsumer vertexConsumer, Matrix4f matrix, int light, int color, float x, float y, float z, float width, float height) {
        // Front face
        vertexConsumer.vertex(matrix, x, y, z).color(color).light(light).next();
        vertexConsumer.vertex(matrix, x + width, y, z).color(color).light(light).next();
        vertexConsumer.vertex(matrix, x + width, y + height, z).color(color).light(light).next();
        
        vertexConsumer.vertex(matrix, x, y, z).color(color).light(light).next();
        vertexConsumer.vertex(matrix, x + width, y + height, z).color(color).light(light).next();
        vertexConsumer.vertex(matrix, x, y + height, z).color(color).light(light).next();
    }

    private static java.nio.file.Path getTextureId() {
        return java.nio.file.Paths.get("diaper");
    }
}
