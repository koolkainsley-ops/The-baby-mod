package com.babyrattle.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import com.babyrattle.status.BabyEffects;

public class BabyBottleItem extends Item {
    public BabyBottleItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && BabyEffects.isBaby(user)) {
            BabyEffects.feedBaby(user);
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
