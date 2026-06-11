package com.babyrattle.network;

import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.network.PacketByteBuf;

import com.babyrattle.BabyRattleModInit;

public class BabyModNetworking {
	public static final Identifier TRANSFORM_PACKET_ID = new Identifier(BabyRattleModInit.MOD_ID, "transform");

	public static void sendTransformRequest() {
		ClientPlayNetworking.send(TRANSFORM_PACKET_ID, new PacketByteBuf(net.minecraft.util.math.Vec3d.ZERO));
	}
}
