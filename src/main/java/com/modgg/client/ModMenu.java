package com.modgg.client;

import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * Меню ModGG Client — kats676
 */
public class ModMenu extends Screen {
    public ModMenu() { 
        super(Text.literal("ModGG Client")); 
    }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 60;

        // 1. Кнопка размера рук (CreateHue)
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("CreateHue Scale: " + fmt(ModGGClient.createHue.scale)), btn -> {
                ModGGClient.createHue.scale += 0.1f;
                if (ModGGClient.createHue.scale > 3f) ModGGClient.createHue.scale = 0.5f;
                btn.setMessage(Text.literal("CreateHue Scale: " + fmt(ModGGClient.createHue.scale)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        // 2. Кнопка зрения в лаве (LavaCam) - ИСПРАВЛЕНО ОБРАЩЕНИЕ
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("LavaCam: " + onOff(ModGGClient.lavaCam.enabled)), btn -> {
                ModGGClient.lavaCam.toggle();
                btn.setMessage(Text.literal("LavaCam: " + onOff(ModGGClient.lavaCam.enabled)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        // 3. Кнопка фейерверков при убийстве (Fireworks)
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Fireworks: " + onOff(ModGGClient.effects.fireworks)), btn -> {
                ModGGClient.effects.fireworks = !ModGGClient.effects.fireworks;
                btn.setMessage(Text.literal("Fireworks: " + onOff(ModGGClient.effects.fireworks)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        // 4. Кнопка подсветки игроков в прицеле (Glow)
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Glow: " + onOff(ModGGClient.glow.enabled)), btn -> {
                ModGGClient.glow.enabled = !ModGGClient.glow.enabled;
                btn.setMessage(Text.literal("Glow: " + onOff(ModGGClient.glow.enabled)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        // 5. Кнопка закрытия меню
        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Закрыть"), btn -> this.close()).dimensions(x, y, 200, 20).build());
    }

    // Отрисовка темного заднего фона при открытии меню (чтобы кнопки было видно)
    @Override
    public void render(net.minecraft.client.gui.DrawContext context, int mouseX, int mouseY, float delta) {
        this.renderBackground(context, mouseX, mouseY, delta);
        context.drawCenteredTextWithShadow(this.textRenderer, this.title, this.width / 2, 20, 0xFFFFFF);
        super.render(context, mouseX, mouseY, delta);
    }

    private String fmt(float f) { return String.format(java.util.Locale.US, "%.1f", f); }
    private String onOff(boolean b) { return b ? "ON" : "OFF"; }
}
