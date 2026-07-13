package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * kats676 — Подсветка игрока в прицеле
 */
public class EffectsPlayers {
    public boolean enabled = false;
    private PlayerEntity lastTargetedPlayer = null;

    public void onTick() {
        var client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        if (!enabled) {
            clearOldHighlight();
            return;
        }

        if (client.targetedEntity instanceof PlayerEntity currentPlayer) {
            if (currentPlayer != lastTargetedPlayer) {
                clearOldHighlight();
                currentPlayer.setGlowing(true);
                lastTargetedPlayer = currentPlayer;
            }
        } else {
            clearOldHighlight();
        }
    }

    private void clearOldHighlight() {
        if (lastTargetedPlayer != null) {
            lastTargetedPlayer.setGlowing(false);
            lastTargetedPlayer = null;
        }
    }
}
