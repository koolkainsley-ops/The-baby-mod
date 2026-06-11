package com.babyrattle.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.util.Identifier;

import com.babyrattle.BabyRattleModInit;
import com.babyrattle.entity.TransformationBeam;

public class BabyRattleItem extends Item {
	public BabyRattleItem(Settings settings) {
		super(settings);
	}

	@Override
	public ActionResult use(World world, PlayerEntity user, Hand hand) {
		if (!world.isClient && user instanceof ServerPlayerEntity player) {
			// Shoot transformation beam
			TransformationBeam beam = new TransformationBeam(world, player);
			world.spawnEntity(beam);
			return ActionResult.SUCCESS;
		}
		return ActionResult.PASS;
	}

	@Override
	public int getMaxUseTime(ItemStack stack) {
		return 72000;
	}
}
