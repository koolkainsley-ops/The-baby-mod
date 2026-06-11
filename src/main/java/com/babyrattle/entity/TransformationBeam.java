package com.babyrattle.entity;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.util.hit.EntityHitResult;
import net.minecraft.world.World;
import net.minecraft.util.math.Vec3d;

import com.babyrattle.status.BabyEffects;

public class TransformationBeam extends ProjectileEntity {
	private PlayerEntity owner;

	public TransformationBeam(EntityType<?> entityType, World world) {
		super(entityType, world);
	}

	public TransformationBeam(World world, PlayerEntity owner) {
		super(EntityType.ARROW, world);
		this.owner = owner;
		this.setPosition(owner.getEyePos());
		Vec3d direction = owner.getRotationVec(1.0f);
		this.setVelocity(direction.multiply(2.0));
	}

	@Override
	protected void onEntityHit(EntityHitResult entityHitResult) {
		if (!this.getWorld().isClient) {
			Entity entity = entityHitResult.getEntity();

			if (entity == this.owner) {
				// Transform the player shooting themselves
				if (entity instanceof PlayerEntity player) {
					BabyEffects.transformPlayerToBaby(player);
				}
			} else if (entity instanceof LivingEntity livingEntity && entity != owner) {
				// Transform other entities
				BabyEffects.transformEntityToBaby(livingEntity);
			}

			this.discard();
		}
	}

	@Override
	public void tick() {
		super.tick();
		if (this.age > 200) {
			this.discard();
		}
	}
}
