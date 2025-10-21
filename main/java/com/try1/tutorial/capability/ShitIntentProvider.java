package com.try1.tutorial.capability;

import com.try1.tutorial.TutorialMod;
import net.minecraft.core.Direction;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.world.entity.Entity;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.common.capabilities.*;
import net.minecraftforge.common.util.LazyOptional;
import net.minecraftforge.event.AttachCapabilitiesEvent;

public class ShitIntentProvider implements ICapabilitySerializable<CompoundTag> {
    public static final ResourceLocation NAME = new ResourceLocation(TutorialMod.MOD_ID, "shit_intent");
    public static final Capability<ShitIntent> INSTANCE = CapabilityManager.get(new CapabilityToken<>() {});

    private final ShitIntent intent = new ShitIntent();
    private final LazyOptional<ShitIntent> lazy = LazyOptional.of(() -> intent);

    @Override
    public <T> LazyOptional<T> getCapability(Capability<T> cap, Direction side) {
        return cap == INSTANCE ? lazy.cast() : LazyOptional.empty();
    }

    @Override
    public CompoundTag serializeNBT() {
        CompoundTag tag = new CompoundTag();
        tag.putInt("value", intent.get());
        return tag;
    }

    @Override
    public void deserializeNBT(CompoundTag nbt) {
        intent.set(nbt.getInt("value"));
    }

    // 自动附加到玩家
    public static void attach(AttachCapabilitiesEvent<Entity> event) {
        if (event.getObject() instanceof Player) {
            event.addCapability(NAME, new ShitIntentProvider());
        }
    }
}