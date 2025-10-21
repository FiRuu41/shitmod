package com.try1.tutorial.client;

import com.mojang.blaze3d.systems.RenderSystem;
import com.try1.tutorial.TutorialMod;
import com.try1.tutorial.capability.ShitIntentProvider;
import net.minecraft.client.Minecraft;
import net.minecraft.world.entity.player.Player;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterGuiOverlaysEvent;
import net.minecraftforge.client.event.RenderGuiOverlayEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

public class ShitHUDOverlay {
    public static void onRegisterHUD(RegisterGuiOverlaysEvent event) {
        event.registerAboveAll(TutorialMod.MOD_ID + ":shit_bar", (gui, poseStack, partialTicks, width, height) -> {
            Minecraft mc = Minecraft.getInstance();
            Player player = mc.player;
            if (player == null) return;
            player.getCapability(ShitIntentProvider.INSTANCE).ifPresent(intent -> {
                int x = width / 2 - 91;
                int y = height - 59;
                int barLen = 81 * intent.get() / intent.getMax();
                RenderSystem.setShaderColor(1.0F, 1.0F, 0.7F, 1.0F);
                gui.fill(x, y, x + barLen, y + 5, 0xFFA0522D); // 棕色
            });
        });
    }
}