package com.try1.tutorial.item;

import com.try1.tutorial.entity.ModEntities;
import com.try1.tutorial.entity.ShitEntity;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResultHolder;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.sounds.SoundEvents;
import net.minecraft.sounds.SoundSource;

public class ShitItem extends Item {
    public ShitItem(Properties props) { super(props); }

    @Override
    public InteractionResultHolder<ItemStack> use(Level level, Player player, InteractionHand hand) {
        ItemStack stack = player.getItemInHand(hand);
        if (!level.isClientSide) {
            ShitEntity shit = new ShitEntity(ModEntities.SHIT.get(), player, level);
            shit.shootFromRotation(player, player.getXRot(), player.getYRot(), 0.0F, 1.5F, 1.0F);
            level.addFreshEntity(shit);
            level.playSound(null, player.getX(), player.getY(), player.getZ(), SoundEvents.SLIME_JUMP, SoundSource.PLAYERS, 0.7F, 1.0F);
            if (!player.getAbilities().instabuild) stack.shrink(1);
        }
        return InteractionResultHolder.sidedSuccess(stack, level.isClientSide());
    }
}