package com.babyrattle;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.item.v1.FabricItemSettings;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.babyrattle.item.BabyRattleItem;
import com.babyrattle.entity.BabyBottleProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class BabyRattleModInit implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("babyrattemod");
	public static final String MOD_ID = "babyrattemod";

	public static final Item BABY_RATTLE = new BabyRattleItem(new FabricItemSettings().maxCount(1));

	public static final EntityType<BabyBottleProjectile> BABY_BOTTLE_PROJECTILE = Registry.register(
		Registries.ENTITY_TYPE,
		new Identifier(MOD_ID, "baby_bottle"),
		FabricEntityTypeBuilder.create(SpawnGroup.MISC, BabyBottleProjectile::new)
			.dimensions(EntityDimensions.fixed(0.25f, 0.25f))
			.trackRangeBlocks(4).trackedUpdateRate(10)
			.build()
	);

	@Override
	public void onInitialize() {
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "baby_rattle"), BABY_RATTLE);
		LOGGER.info("Baby Rattle Mod initialized!");
	}
}
