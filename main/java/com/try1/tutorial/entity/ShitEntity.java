package com.try1.tutorial.entity;

import com.try1.tutorial.item.ModItems;
import net.minecraft.world.entity.EntityType;
import net.minecraft.world.entity.LivingEntity;
import net.minecraft.world.entity.projectile.ThrowableItemProjectile;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.Level;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.phys.EntityHitResult;
import net.minecraft.world.phys.HitResult;

public class ShitEntity extends ThrowableItemProjectile {
    public ShitEntity(EntityType<? extends ShitEntity> type, Level level) { super(type, level); }
    public ShitEntity(EntityType<? extends ShitEntity> type, LivingEntity owner, Level level) {
        super(type, owner, level);
    }
    @Override
    protected Item getDefaultItem() { return ModItems.SHIT.get(); }

    @Override
    protected void onHitEntity(EntityHitResult result) {
        if (!level().isClientSide) {
            if (result.getEntity() instanceof Player p) {
                p.addEffect(new MobEffectInstance(MobEffects.CONFUSION, 200, 0)); // 10秒反胃
                p.addEffect(new MobEffectInstance(MobEffects.WEAKNESS, 200, 0));
            } else if (result.getEntity() instanceof LivingEntity le) {
                le.kill(); // 秒杀
            }
            this.discard();
        }
    }

    @Override
    protected void onHit(HitResult pResult) {
        super.onHit(pResult);
        if (!level().isClientSide) this.discard();
    }
}