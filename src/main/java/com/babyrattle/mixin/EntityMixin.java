package com.babyrattle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.nbt.NbtCompound;

import com.babyrattle.status.BabyEffects;

@Mixin(LivingEntity.class)
public abstract class EntityMixin {
	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo ci) {
		LivingEntity entity = (LivingEntity) (Object) this;

		if (BabyEffects.isBaby(entity)) {
			if (entity.age % 40 == 0) {
				if (entity instanceof net.minecraft.entity.player.PlayerEntity player) {
					BabyEffects.makeBabyBurp(player);
				}
			}
		}
	}
}
