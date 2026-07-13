package com.modgg.client.modules;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.gui.DrawContext;
import net.minecraft.item.Items;
import net.minecraft.util.Identifier;
import net.minecraft.util.math.MathHelper;

/**
 * kats676 — Модельки на экране (Сердце и Тотем)
 */
public class HueOptimizatec {
    public boolean enabled = true;

    // ИСПРАВЛЕНО: В 1.21.4 используется Identifier.of вместо new Identifier
    private static final Identifier HEART_TEXTURE = Identifier.of("minecraft", "textures/gui/sprites/hud/heart/full.png");

    public void render(DrawContext context, float tickDelta) {
        if (!enabled) return;

        var client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        float health = client.player.getHealth();
        float maxHealth = client.player.getMaxHealth();
        
        int heartX = 20;
        int heartY = 40;
        int heartSize = 24;

        float scale = 1.0f;
        if (health / maxHealth <= 0.3f) {
            scale = 1.0f + MathHelper.sin((client.player.age + tickDelta) * 0.5f) * 0.15f;
        }

        context.getMatrices().push();
        context.getMatrices().translate(heartX + heartSize / 2f, heartY + heartSize / 2f, 0);
        context.getMatrices().scale(scale, scale, 1.0f);
        context.getMatrices().translate(-(heartX + heartSize / 2f), -(heartY + heartSize / 2f), 0);
        
        context.drawTexture(HEART_TEXTURE, heartX, heartY, 0, 0, heartSize, heartSize, heartSize, heartSize);
        context.getMatrices().pop();

        String healthText = String.format("%.1f", health);
        context.drawText(client.textRenderer, healthText, heartX + heartSize + 6, heartY + (heartSize / 2) - 4, 0xFFFF5555, true);

        int totemX = 20;
        int totemY = heartY + heartSize + 10;

        context.drawItem(Items.TOTEM_OF_UNDYING.getDefaultStack(), totemX, totemY);
        
        int totemCount = client.player.getInventory().count(Items.TOTEM_OF_UNDYING);
        if (client.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            totemCount += 1;
        }

        String totemText = "x" + totemCount;
        context.drawText(client.textRenderer, totemText, totemX + 22, totemY + 4, 0xFF55FF55, true);
    }
}
