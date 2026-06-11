package com.babyrattle.item;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

import com.babyrattle.status.BabyEffects;

public class RattleToyItem extends Item {
    public RattleToyItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult use(World world, PlayerEntity user, Hand hand) {
        if (!world.isClient && BabyEffects.isBaby(user)) {
            // Play rattle sound
            world.playSound(
                null,
                user.getX(),
                user.getY(),
                user.getZ(),
                net.minecraft.sound.SoundEvents.BLOCK_NOTE_BLOCK_XYLOPHONE.value(),
                net.minecraft.sound.SoundCategory.PLAYERS,
                1.0f,
                1.5f
            );
            return ActionResult.SUCCESS;
        }
        return ActionResult.PASS;
    }
}
