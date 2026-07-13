package com.modgg.client;

import com.modgg.client.modules.*;
import net.minecraft.client.gui.screen.Screen;
import net.minecraft.client.gui.widget.ButtonWidget;
import net.minecraft.text.Text;

/**
 * Меню ModGG Client — kats676
 */
public class ModMenu extends Screen {
    public ModMenu() { super(Text.literal("ModGG Client")); }

    @Override
    protected void init() {
        int x = this.width / 2 - 100;
        int y = this.height / 2 - 60;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("CreateHue Scale: " + fmt(ModGGClient.createHue.scale)), btn -> {
                ModGGClient.createHue.scale += 0.1f;
                if (ModGGClient.createHue.scale > 3f) ModGGClient.createHue.scale = 0.5f;
                btn.setMessage(Text.literal("Scale: " + fmt(ModGGClient.createHue.scale)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("LavaCam: " + onOff(LavaCam.enabled)), btn -> {
                ModGGClient.lavaCam.toggle();
                btn.setMessage(Text.literal("LavaCam: " + onOff(LavaCam.enabled)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Fireworks: " + onOff(ModGGClient.effects.fireworks)), btn -> {
                ModGGClient.effects.fireworks = !ModGGClient.effects.fireworks;
                btn.setMessage(Text.literal("Fireworks: " + onOff(ModGGClient.effects.fireworks)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Glow: " + onOff(ModGGClient.glow.enabled)), btn -> {
                ModGGClient.glow.enabled = !ModGGClient.glow.enabled;
                btn.setMessage(Text.literal("Glow: " + onOff(ModGGClient.glow.enabled)));
            }).dimensions(x, y, 200, 20).build());
        y += 25;

        this.addDrawableChild(ButtonWidget.builder(
            Text.literal("Закрыть"), btn -> close()).dimensions(x, y, 200, 20).build());
    }

    private String fmt(float f) { return String.format("%.1f", f); }
    private String onOff(boolean b) { return b ? "ON" : "OFF"; }
}
