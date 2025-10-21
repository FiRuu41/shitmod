package com.try1.tutorial.network;

import net.minecraftforge.network.NetworkEvent;
import net.minecraft.server.level.ServerPlayer;

import java.util.function.Supplier;

public class ShitKeyPacket {
    public ShitKeyPacket() {}

    public void handle(Supplier<NetworkEvent.Context> ctxSupplier) {
        NetworkEvent.Context ctx = ctxSupplier.get();
        ctx.enqueueWork(() -> {
            ServerPlayer player = ctx.getSender();
            if (player == null) return;
            // 你的服务器端逻辑
        });
        ctx.setPacketHandled(true);
    }
}