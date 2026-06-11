package com.babyrattle.status;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.text.Text;

public class BabyEffects {
	public static void transformPlayerToBaby(PlayerEntity player) {
		NbtCompound tag = player.getPersistentData();
		tag.putBoolean("baby", true);

		// Apply buffs
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.RESISTANCE, 36000, 4, false, false));
		player.addStatusEffect(new StatusEffectInstance(StatusEffects.SLOWNESS, 36000, 1, false, false));

		player.sendMessage(Text.literal("You are now a baby!"), false);
	}

	public static void transformEntityToBaby(LivingEntity entity) {
		NbtCompound tag = entity.getPersistentData();
		tag.putBoolean("baby", true);

		// Make mobs not attack
		entity.addStatusEffect(new StatusEffectInstance(StatusEffects.WEAKNESS, 36000, 2, false, false));
	}

	public static void makeBabyBurp(PlayerEntity player) {
		if (isBaby(player)) {
			player.getWorld().playSound(
				null,
				player.getX(),
				player.getY(),
				player.getZ(),
				net.minecraft.sound.SoundEvents.ENTITY_PLAYER_BURP,
				net.minecraft.sound.SoundCategory.PLAYERS,
				1.0f,
				1.0f
			);
		}
	}

	public static void feedBaby(PlayerEntity player) {
		if (isBaby(player)) {
			player.heal(3);
			player.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 600, 1, false, false));
			player.getWorld().playSound(
				null,
				player.getX(),
				player.getY(),
				player.getZ(),
				net.minecraft.sound.SoundEvents.ENTITY_GENERIC_EAT,
				net.minecraft.sound.SoundCategory.PLAYERS,
				1.0f,
				1.0f
			);
		}
	}

	public static boolean isBaby(LivingEntity entity) {
		NbtCompound tag = entity.getPersistentData();
		return tag.getBoolean("baby");
	}
}
