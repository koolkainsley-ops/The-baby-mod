package com.babyrattle.network;

import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;
import net.minecraft.network.PacketByteBuf;

import com.babyrattle.BabyRattleModInit;
import com.babyrattle.status.BabyEffects;

public class ServerNetworking {
    public static final Identifier BABY_BURP_PACKET = new Identifier(BabyRattleModInit.MOD_ID, "baby_burp");
    public static final Identifier BABY_FEED_PACKET = new Identifier(BabyRattleModInit.MOD_ID, "baby_feed");

    public static void registerServerReceivers() {
        ServerPlayNetworking.registerGlobalReceiver(BABY_BURP_PACKET, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                if (BabyEffects.isBaby(player)) {
                    BabyEffects.makeBabyBurp(player);
                }
            });
        });

        ServerPlayNetworking.registerGlobalReceiver(BABY_FEED_PACKET, (server, player, handler, buf, responseSender) -> {
            server.execute(() -> {
                if (BabyEffects.isBaby(player)) {
                    BabyEffects.feedBaby(player);
                }
            });
        });
    }
}
