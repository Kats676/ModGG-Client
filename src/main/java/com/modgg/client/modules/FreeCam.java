package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

/**
 * kats676 — Свободная камера
 */
public class FreeCam {
    public boolean enabled = false;
    private Vec3d pos;
    private float yaw, pitch;

    public void toggle() {
        var p = MinecraftClient.getInstance().player;
        if (p == null) return;
        enabled = !enabled;
        if (enabled) { pos = p.getPos(); yaw = p.getYaw(); pitch = p.getPitch(); }
        else { p.setPos(pos.x, pos.y, pos.z); p.setYaw(yaw); p.setPitch(pitch); }
    }
}
