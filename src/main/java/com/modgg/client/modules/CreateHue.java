package com.modgg.client.modules;

import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.util.math.RotationAxis;

/**
 * kats676 — Редактор рук
 */
public class CreateHue {
    public boolean enabled = false;
    public float scale = 0.7f; // Уменьшенный размер по умолчанию
    public float offX = 0.2f, offY = -0.1f, offZ = -0.2f;
    public float rotX = 10f, rotY = 0f, rotZ = 0f;

    public void apply(MatrixStack m) {
        if (!enabled) return;
        m.scale(scale, scale, scale);
        m.translate(offX, offY, offZ);
        m.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotX));
        m.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotY));
        m.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotZ));
    }
}
