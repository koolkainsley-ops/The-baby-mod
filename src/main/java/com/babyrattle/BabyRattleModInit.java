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
import com.babyrattle.item.BabyBottleItem;
import com.babyrattle.item.PacifierItem;
import com.babyrattle.item.TeddyBearItem;
import com.babyrattle.item.DiaperItem;
import com.babyrattle.item.MilkBucketItem;
import com.babyrattle.item.RattleToyItem;
import com.babyrattle.entity.BabyBottleProjectile;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;
import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

public class BabyRattleModInit implements ModInitializer {
	public static final Logger LOGGER = LoggerFactory.getLogger("babyrattemod");
	public static final String MOD_ID = "babyrattemod";

	public static final Item BABY_RATTLE = new BabyRattleItem(new FabricItemSettings().maxCount(1));
	public static final Item BABY_BOTTLE = new BabyBottleItem(new FabricItemSettings().maxCount(1));
	public static final Item PACIFIER = new PacifierItem(new FabricItemSettings().maxCount(1));
	public static final Item TEDDY_BEAR = new TeddyBearItem(new FabricItemSettings().maxCount(1));
	public static final Item DIAPER = new DiaperItem(new FabricItemSettings().maxCount(64));
	public static final Item MILK_BUCKET = new MilkBucketItem(new FabricItemSettings().maxCount(1));
	public static final Item RATTLE_TOY = new RattleToyItem(new FabricItemSettings().maxCount(1));

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
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "baby_bottle"), BABY_BOTTLE);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "pacifier"), PACIFIER);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "teddy_bear"), TEDDY_BEAR);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "diaper"), DIAPER);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "milk_bucket"), MILK_BUCKET);
		Registry.register(Registries.ITEM, new Identifier(MOD_ID, "rattle_toy"), RATTLE_TOY);
		LOGGER.info("Baby Rattle Mod initialized!");
	}
}
