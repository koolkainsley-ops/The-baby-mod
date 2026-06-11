package com.babyrattle.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import com.babyrattle.status.BabyEffects;

public class PacifierItem extends Item {
    public PacifierItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && BabyEffects.isBaby(user)) {
            BabyEffects.makeBabyBurp(user);
            user.addStatusEffect(new net.minecraft.entity.effect.StatusEffectInstance(
                net.minecraft.entity.effect.StatusEffects.REGENERATION, 200, 1, false, false
            ));
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
