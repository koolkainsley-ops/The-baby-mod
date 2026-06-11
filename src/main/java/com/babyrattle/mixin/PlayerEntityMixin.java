package com.babyrattle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.Entity;

import com.babyrattle.status.BabyEffects;

@Mixin(PlayerEntity.class)
public abstract class PlayerEntityMixin {
	@Inject(method = "damage", at = @At("HEAD"), cancellable = true)
	private void onDamage(DamageSource source, float amount, CallbackInfoReturnable<Boolean> cir) {
		PlayerEntity player = (PlayerEntity) (Object) this;

		if (BabyEffects.isBaby(player) && source.getAttacker() != null) {
			// Baby takes no damage, attacker dies instead
			Entity attacker = source.getAttacker();
			attacker.kill();
			cir.setReturnValue(false);
		}
	}

	@Inject(method = "tick", at = @At("HEAD"))
	private void onTick(CallbackInfo ci) {
		PlayerEntity player = (PlayerEntity) (Object) this;

		if (BabyEffects.isBaby(player)) {
			// Crying animation
			if (player.age % 30 == 0) {
				player.swingHand(player.getActiveHand());
			}
		}
	}
}
