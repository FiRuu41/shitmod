package com.try1.tutorial.entity.client;

import com.try1.tutorial.entity.ShitEntity;
import net.minecraft.client.renderer.entity.ThrownItemRenderer;
import net.minecraft.client.renderer.entity.EntityRendererProvider;

public class ShitEntityRenderer extends ThrownItemRenderer<ShitEntity> {
    public ShitEntityRenderer(EntityRendererProvider.Context context) {
        super(context, 1.0f, false);
    }
}