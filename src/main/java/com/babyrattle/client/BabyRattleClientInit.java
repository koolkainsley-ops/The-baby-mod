package com.babyrattle.client;

import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.api.EnvType;
import net.fabricmc.api.Environment;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import org.lwjgl.glfw.GLFW;

import com.babyrattle.network.BabyModNetworking;
import com.babyrattle.client.gui.BabyWeaponGui;

public class BabyRattleClientInit implements ClientModInitializer {
	public static KeyBinding transformKey;
	public static KeyBinding burpKey;
	public static KeyBinding feedKey;
	public static KeyBinding weaponGuiKey;

	@Override
	public void onInitializeClient() {
		transformKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.babyrattemod.transform",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_R,
			"category.babyrattemod"
		));

		burpKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.babyrattemod.burp",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_B,
			"category.babyrattemod"
		));

		feedKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.babyrattemod.feed",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_F,
			"category.babyrattemod"
		));

		weaponGuiKey = KeyBindingHelper.registerKeyBinding(new KeyBinding(
			"key.babyrattemod.weapon_gui",
			InputUtil.Type.KEYSYM,
			GLFW.GLFW_KEY_V,
			"category.babyrattemod"
		));

		ClientTickEvents.END_CLIENT_TICK.register(client -> {
			if (transformKey.wasPressed()) {
				BabyModNetworking.sendTransformRequest();
			}
			if (burpKey.wasPressed()) {
				BabyModNetworking.sendBurpRequest();
			}
			if (feedKey.wasPressed()) {
				BabyModNetworking.sendFeedRequest();
			}
			if (weaponGuiKey.wasPressed() && client.player != null) {
				client.setScreen(new BabyWeaponGui());
			}
		});
	}
}
