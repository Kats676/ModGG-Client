package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;

/**
 * kats676 — Эффекты убийств
 */
public class Effects {
    public boolean fireworks = false;

    public void onKill(PlayerEntity victim) {
        if (!fireworks) return;
        var w = MinecraftClient.getInstance().world;
        if (w == null) return;
        for (int i = 0; i < 50; i++)
            w.addParticle(ParticleTypes.FIREWORK,
                victim.getX() + (Math.random() - 0.5) * 2,
                victim.getY() + Math.random() * 2,
                victim.getZ() + (Math.random() - 0.5) * 2,
                0, 0, 0);
        w.playSound(null, victim.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST,
            victim.getSoundCategory(), 1f, 1f);
    }
}
