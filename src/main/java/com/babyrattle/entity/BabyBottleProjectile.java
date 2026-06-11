package com.babyrattle.entity;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.projectile.ProjectileEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.world.World;
import net.minecraft.util.math.Box;
import net.minecraft.util.math.Vec3d;
import java.util.List;

public class BabyBottleProjectile extends ProjectileEntity {
	private int lifespan = 100;

	public BabyBottleProjectile(EntityType<?> type, World world) {
		super(type, world);
	}

	public BabyBottleProjectile(World world, double x, double y, double z) {
		super(EntityType.FALLING_BLOCK, world);
		this.setPosition(x, y, z);
	}

	@Override
	public void tick() {
		super.tick();

		if (!this.getWorld().isClient) {
			lifespan--;

			// Check for entities in front and kill them
			Vec3d pos = this.getPos();
			Box area = new Box(pos.x - 2, pos.y - 2, pos.z - 2, pos.x + 2, pos.y + 2, pos.z + 2);
			List<LivingEntity> entities = this.getWorld().getEntitiesByClass(LivingEntity.class, area, entity -> entity != this.getOwner());

			for (LivingEntity entity : entities) {
				if (!entity.isInvulnerable()) {
					entity.kill();
				}
			}

			if (lifespan <= 0) {
				this.discard();
			}
		}
	}
}
