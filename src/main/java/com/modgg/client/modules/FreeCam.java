package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.util.math.Vec3d;

/**
 * kats676 — Свободная камера (Рабочая версия)
 */
public class FreeCam {
    public boolean enabled = false;
    private Vec3d pos;
    private float yaw, pitch;

    // Фейковые координаты для полёта камеры
    public double fakeX, fakeY, fakeZ;
    public float fakeYaw, fakePitch;

    public void toggle() {
        var client = MinecraftClient.getInstance();
        var p = client.player;
        if (p == null) return;

        enabled = !enabled;
        if (enabled) {
            // Запоминаем настоящую позицию игрока, чтобы вернуть его назад
            pos = p.getPos();
            yaw = p.getYaw();
            pitch = p.getPitch();

            // Инициализируем позицию свободной камеры там же, где стоит игрок
            fakeX = p.getX();
            fakeY = p.getY();
            fakeZ = p.getZ();
            fakeYaw = p.getYaw();
            fakePitch = p.getPitch();
            
            p.noClip = true; // Отключаем коллизию, чтобы не застревать
        } else {
            // Возвращаем игрока обратно на чекпоинт
            p.setPos(pos.x, pos.y, pos.z);
            p.setYaw(yaw);
            p.setPitch(pitch);
            p.setVelocity(0, 0, 0);
            p.noClip = false;
        }
    }
}
