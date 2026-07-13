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

    // Иконка стандартного сердечка из ванильного Майнкрафта
    private static final Identifier HEART_TEXTURE = new Identifier("minecraft", "textures/gui/sprites/hud/heart/full.png");

    public void render(DrawContext context, float tickDelta) {
        if (!enabled) return;

        var client = MinecraftClient.getInstance();
        if (client.player == null || client.world == null) return;

        // Получаем здоровье игрока
        float health = client.player.getHealth();
        float maxHealth = client.player.getMaxHealth();
        
        // 1. ОТРИСОВКА БОЛЬШОГО СЕРДЦА
        // Координаты на экране (левый верхний угол, чуть отступив)
        int heartX = 20;
        int heartY = 40;
        int heartSize = 24; // Размер сердечка на экране

        // Эффект пульсации: если здоровья мало (меньше 30%), сердце начнет быстро биться
        float scale = 1.0f;
        if (health / maxHealth <= 0.3f) {
            scale = 1.0f + MathHelper.sin((client.player.age + tickDelta) * 0.5f) * 0.15f;
        }

        // Рисуем сердце с учетом пульсации
        context.getMatrices().push();
        context.getMatrices().translate(heartX + heartSize / 2f, heartY + heartSize / 2f, 0);
        context.getMatrices().scale(scale, scale, 1.0f);
        context.getMatrices().translate(-(heartX + heartSize / 2f), -(heartY + heartSize / 2f), 0);
        
        context.drawTexture(HEART_TEXTURE, heartX, heartY, 0, 0, heartSize, heartSize, heartSize, heartSize);
        context.getMatrices().pop();

        // Пишем текст с количеством ХП рядом с сердцем
        String healthText = String.format("%.1f", health);
        context.drawText(client.textRenderer, healthText, heartX + heartSize + 6, heartY + (heartSize / 2) - 4, 0xFFFF5555, true);


        // 2. ОТРИСОВКА ТОТЕМА НА ЭКРАНЕ
        // Рисуем 3D-модельку тотема из инвентаря прямо в интерфейсе
        int totemX = 20;
        int totemY = heartY + heartSize + 10; // Располагаем строго под сердцем

        // Рисуем сам предмет (тотем)
        context.drawItem(Items.TOTEM_OF_UNDYING.getDefaultStack(), totemX, totemY);
        
        // Считаем, сколько всего тотемов есть у вас в инвентаре
        int totemCount = client.player.getInventory().count(Items.TOTEM_OF_UNDYING);
        if (client.player.getOffHandStack().isOf(Items.TOTEM_OF_UNDYING)) {
            totemCount += 1; // Учитываем тотем в левой руке
        }

        // Пишем количество тотемов рядом с иконкой
        String totemText = "x" + totemCount;
        context.drawText(client.textRenderer, totemText, totemX + 22, totemY + 4, 0xFF55FF55, true);
    }
}
