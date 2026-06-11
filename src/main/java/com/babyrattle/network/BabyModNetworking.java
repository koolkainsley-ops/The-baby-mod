package com.babyrattle.network;

import net.fabricmc.fabric.api.networking.v1.ClientPlayNetworking;
import net.minecraft.util.Identifier;
import net.minecraft.network.PacketByteBuf;
import io.netty.buffer.Unpooled;

import com.babyrattle.BabyRattleModInit;

public class BabyModNetworking {
	public static final Identifier TRANSFORM_PACKET_ID = new Identifier(BabyRattleModInit.MOD_ID, "transform");
	public static final Identifier BURP_PACKET_ID = new Identifier(BabyRattleModInit.MOD_ID, "baby_burp");
	public static final Identifier FEED_PACKET_ID = new Identifier(BabyRattleModInit.MOD_ID, "baby_feed");

	public static void sendTransformRequest() {
		ClientPlayNetworking.send(TRANSFORM_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
	}

	public static void sendBurpRequest() {
		ClientPlayNetworking.send(BURP_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
	}

	public static void sendFeedRequest() {
		ClientPlayNetworking.send(FEED_PACKET_ID, new PacketByteBuf(Unpooled.buffer()));
	}
}
