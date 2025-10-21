package com.try1.tutorial.client;

import net.minecraft.client.KeyMapping;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.client.event.RegisterKeyMappingsEvent;
import net.minecraftforge.fml.common.Mod;
import org.lwjgl.glfw.GLFW;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD, value = Dist.CLIENT)
public class KeyBindInit {
    public static final KeyMapping SHIT_KEY = new KeyMapping("key.tutorial_mod.shit", GLFW.GLFW_KEY_CAPS_LOCK, "key.category.tutorial_mod");

    public static void register(RegisterKeyMappingsEvent event) {
        event.register(SHIT_KEY);
    }
}