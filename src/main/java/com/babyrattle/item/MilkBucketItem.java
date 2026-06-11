package com.babyrattle.item;

import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import com.babyrattle.status.BabyEffects;

public class MilkBucketItem extends Item {
    public MilkBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && BabyEffects.isBaby(user)) {
            user.heal(4);
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.SPEED, 400, 1, false, false));
            user.addStatusEffect(new StatusEffectInstance(StatusEffects.STRENGTH, 300, 0, false, false));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
