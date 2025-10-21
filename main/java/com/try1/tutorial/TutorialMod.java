package com.try1.tutorial;

import com.mojang.logging.LogUtils;
import com.try1.tutorial.capability.ShitIntentProvider;
import com.try1.tutorial.client.KeyBindInit;
import com.try1.tutorial.client.ShitHUDOverlay;
import com.try1.tutorial.entity.ModEntities;
import com.try1.tutorial.item.ModItems;
import com.try1.tutorial.network.ModMessages;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.AttachCapabilitiesEvent;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.fml.ModLoadingContext;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.config.ModConfig;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import org.slf4j.Logger;

@Mod(TutorialMod.MOD_ID)
public class TutorialMod {
    public static final String MOD_ID = "tutorial_mod";
    public static final Logger LOGGER = LogUtils.getLogger();

    public TutorialMod() {
        IEventBus modEventBus = FMLJavaModLoadingContext.get().getModEventBus();

        ModItems.register(modEventBus);
        ModEntities.register(modEventBus);

        MinecraftForge.EVENT_BUS.register(this);

        modEventBus.addListener(KeyBindInit::register);
        modEventBus.addListener(ShitHUDOverlay::onRegisterHUD);

        ModMessages.register();

        // 注册 capability
        modEventBus.addGenericListener(Player.class, ShitIntentProvider::attach);

        // 注册配置文件（可选）
        ModLoadingContext.get().registerConfig(ModConfig.Type.COMMON, Config.SPEC);
    }

    // 屎意与饱食度联动+屎意满buff
    @net.minecraftforge.eventbus.api.SubscribeEvent
    public void onPlayerTick(TickEvent.PlayerTickEvent event) {
        if (event.player.level().isClientSide) return;
        Player player = event.player;
        player.getCapability(ShitIntentProvider.INSTANCE).ifPresent(intent -> {
            int food = player.getFoodData().getFoodLevel();
            if (intent.get() < intent.getMax() && food < 20) {
                intent.add(1);
            }
            if (intent.isFull()) {
                player.addEffect(new net.minecraft.world.effect.MobEffectInstance(
                        net.minecraft.world.effect.MobEffects.MOVEMENT_SLOWDOWN, 40, 0, false, false, true
                ));
            }
        });
    }
}