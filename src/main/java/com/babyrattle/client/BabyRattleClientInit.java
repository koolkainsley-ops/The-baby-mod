package com.babyrattle.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import com.babyrattle.network.BabyModNetworking;

public class BabyRattleClientInit implements ClientModInitializer {
	public static KeyBinding transformKey;

	@Override
	public void onInitializeClient() {
		transformKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.babyrattemod.transform",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_R,
			"category.babyrattemod"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (transformKey.wasPressed()) {
				BabyModNetworking.sendTransformRequest();
			}
		});
	}
}
