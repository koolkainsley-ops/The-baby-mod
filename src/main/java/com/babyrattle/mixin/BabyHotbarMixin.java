package com.babyrattle.mixin;

import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;

import com.babyrattle.BabyRattleModInit;
import com.babyrattle.status.BabyEffects;

@Mixin(PlayerEntity.class)
public class BabyHotbarMixin {

    @Inject(method = "tick", at = @At("HEAD"))
    private void autoFillBabyHotbar(CallbackInfo ci) {
        PlayerEntity player = (PlayerEntity) (Object) this;

        if (player.getWorld().isClient) {
            return;
        }

        if (BabyEffects.isBaby(player)) {
            Inventory inventory = player.getInventory();

            // Fill hotbar slots with baby items
            if (inventory.getStack(0).isEmpty()) {
                inventory.setStack(0, new ItemStack(BabyRattleModInit.BABY_RATTLE));
            }
            if (inventory.getStack(1).isEmpty()) {
                inventory.setStack(1, new ItemStack(BabyRattleModInit.BABY_BOTTLE));
            }
            if (inventory.getStack(2).isEmpty()) {
                inventory.setStack(2, new ItemStack(BabyRattleModInit.PACIFIER));
            }
            if (inventory.getStack(3).isEmpty()) {
                inventory.setStack(3, new ItemStack(BabyRattleModInit.TEDDY_BEAR));
            }
            if (inventory.getStack(4).isEmpty()) {
                inventory.setStack(4, new ItemStack(BabyRattleModInit.DIAPER, 64));
            }
            if (inventory.getStack(5).isEmpty()) {
                inventory.setStack(5, new ItemStack(BabyRattleModInit.MILK_BUCKET));
            }
            if (inventory.getStack(6).isEmpty()) {
                inventory.setStack(6, new ItemStack(BabyRattleModInit.RATTLE_TOY));
            }
        }
    }
}
