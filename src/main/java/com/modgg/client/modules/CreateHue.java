package com.modgg.client.modules;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

/**
 * kats676 — Редактор рук
 */
public class CreateHue {
    public boolean enabled = false;
    public float scale = 1.0f;
    public float offX = 0f, offY = 0f, offZ = 0f;
    public float rotX = 0f, rotY = 0f, rotZ = 0f;

    public void apply(MatrixStack m) {
        if (!enabled) return;
        m.scale(scale, scale, scale);
        m.translate(offX, offY, offZ);
        m.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotX));
        m.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotY));
        m.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotZ));
    }
}
