package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.entity.player.PlayerEntity;

/**
 * kats676 — Подсветка игрока в прицеле
 */
public class EffectsPlayers {
    public boolean enabled = false;
    private PlayerEntity lastTargetedPlayer = null;

    // Этот метод нужно вызывать каждый игровой тик (ClientTickEvents.END_CLIENT_TICK)
    public void onTick() {
        var client = MinecraftClient.getInstance();
        if (client.world == null || client.player == null) return;

        // Если модуль выключен, убираем старую подсветку и ничего не делаем
        if (!enabled) {
            clearOldHighlight();
            return;
        }

        // Проверяем, наведен ли прицел на какую-либо сущность
        if (client.targetedEntity instanceof PlayerEntity currentPlayer) {
            // Если прицел перевелся на НОВОГО игрока
            if (currentPlayer != lastTargetedPlayer) {
                clearOldHighlight(); // Убираем подсветку с прошлого игрока
                currentPlayer.setGlowing(true); // Включаем подсветку новому
                lastTargetedPlayer = currentPlayer; // Запоминаем его
            }
        } else {
            // Если прицел вообще ушел с игроков (в небо, на блок, на моба)
            clearOldHighlight();
        }
    }

    // Вспомогательный метод, чтобы убирать свечение, когда прицел уходит
    private void clearOldHighlight() {
        if (lastTargetedPlayer != null) {
            lastTargetedPlayer.setGlowing(false);
            lastTargetedPlayer = null;
        }
    }
}
