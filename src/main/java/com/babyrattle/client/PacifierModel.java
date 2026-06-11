package com.babyrattle.client;

import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.minecraft.client.render.RenderLayer;
import net.minecraft.client.render.VertexConsumer;
import net.minecraft.client.render.VertexConsumerProvider;
import net.minecraft.client.util.math.MatrixStack;
import org.joml.Matrix4f;

@Environment(EnvType.CLIENT)
public class PacifierModel {

    public static void render(MatrixStack matrices, VertexConsumerProvider vertexConsumers, int light, int color) {
        VertexConsumer vertexConsumer = vertexConsumers.getBuffer(RenderLayer.getEntitySolid(getTextureId()));
        
        // Get the current transformation matrix
        Matrix4f positionMatrix = matrices.peek().getPositionMatrix();
        
        // Draw a simple pacifier shape (sphere for nipple, ring for handle)
        renderPacifierNipple(vertexConsumer, positionMatrix, light, color);
        renderPacifierRing(vertexConsumer, positionMatrix, light, color);
    }

    private static void renderPacifierNipple(VertexConsumer vertexConsumer, Matrix4f matrix, int light, int color) {
        // Render a small sphere for the pacifier nipple
        float size = 0.08f;
        int segments = 8;
        
        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);
            
            float x1 = (float) Math.cos(angle1) * size;
            float z1 = (float) Math.sin(angle1) * size;
            float x2 = (float) Math.cos(angle2) * size;
            float z2 = (float) Math.sin(angle2) * size;
            
            // Top vertex
            vertexConsumer.vertex(matrix, 0, size * 1.5f, 0)
                    .color(color).light(light).next();
            vertexConsumer.vertex(matrix, x1, 0, z1)
                    .color(color).light(light).next();
            vertexConsumer.vertex(matrix, x2, 0, z2)
                    .color(color).light(light).next();
            
            // Bottom vertex
            vertexConsumer.vertex(matrix, 0, -size * 0.5f, 0)
                    .color(color).light(light).next();
            vertexConsumer.vertex(matrix, x2, 0, z2)
                    .color(color).light(light).next();
            vertexConsumer.vertex(matrix, x1, 0, z1)
                    .color(color).light(light).next();
        }
    }

    private static void renderPacifierRing(VertexConsumer vertexConsumer, Matrix4f matrix, int light, int color) {
        // Render the pacifier handle ring
        float radius = 0.12f;
        float ringThickness = 0.03f;
        int segments = 16;
        
        for (int i = 0; i < segments; i++) {
            float angle1 = (float) (2 * Math.PI * i / segments);
            float angle2 = (float) (2 * Math.PI * (i + 1) / segments);
            
            float x1 = (float) Math.cos(angle1) * radius;
            float z1 = (float) Math.sin(angle1) * radius;
            float x2 = (float) Math.cos(angle2) * radius;
            float z2 = (float) Math.sin(angle2) * radius;
            
            // Outer ring
            vertexConsumer.vertex(matrix, x1, -ringThickness, z1)
                    .color(0xFFFF69B4).light(light).next();
            vertexConsumer.vertex(matrix, x2, -ringThickness, z2)
                    .color(0xFFFF69B4).light(light).next();
            vertexConsumer.vertex(matrix, x2, ringThickness, z2)
                    .color(0xFFFF69B4).light(light).next();
            
            vertexConsumer.vertex(matrix, x1, -ringThickness, z1)
                    .color(0xFFFF69B4).light(light).next();
            vertexConsumer.vertex(matrix, x2, ringThickness, z2)
                    .color(0xFFFF69B4).light(light).next();
            vertexConsumer.vertex(matrix, x1, ringThickness, z1)
                    .color(0xFFFF69B4).light(light).next();
        }
    }

    private static java.nio.file.Path getTextureId() {
        // Return a dummy texture ID - the geometry is procedurally rendered
        return java.nio.file.Paths.get("pacifier");
    }
}
