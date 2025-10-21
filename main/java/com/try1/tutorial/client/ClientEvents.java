package com.try1.tutorial.client;

import com.try1.tutorial.network.ModMessages;
import com.try1.tutorial.network.ShitKeyPacket;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.event.TickEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;

@Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.FORGE, value = Dist.CLIENT)
public class ClientEvents {
    @SubscribeEvent
    public static void onClientTick(TickEvent.ClientTickEvent event) {
        if (KeyBindInit.SHIT_KEY.consumeClick()) {
            ModMessages.INSTANCE.sendToServer(new ShitKeyPacket());
        }
    }
}