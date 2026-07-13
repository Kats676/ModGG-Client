package com.modgg.client;

import com.modgg.client.modules.*;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.event.lifecycle.v1.ClientTickEvents;
import net.fabricmc.fabric.api.client.keybinding.v1.KeyBindingHelper;
import net.minecraft.client.option.KeyBinding;
import net.minecraft.client.util.InputUtil;
import net.minecraft.text.Text;
import org.lwjgl.glfw.GLFW;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * ModGG Client
 * Разработчик: kats676
 */
public class ModGGClient implements ModInitializer {
    public static final String MOD_ID = "modgg";
    public static final Logger LOGGER = LoggerFactory.getLogger(MOD_ID);

    public static KeyBinding menuKey;
    public static KeyBinding freeCamKey;
    public static KeyBinding createHueKey;
    public static KeyBinding lavaCamKey;
    public static KeyBinding effectsKey;
    public static KeyBinding glowKey;

    public static CreateHue createHue = new CreateHue();
    public static FreeCam freeCam = new FreeCam();
    public static LavaCam lavaCam = new LavaCam();
    public static Effects effects = new Effects();
    public static EffectsPlayers glow = new EffectsPlayers();
    public static HueOptimizatec hue = new HueOptimizatec();

    @Override
    public void onInitialize() {
        LOGGER.info("ModGG Client by kats676 запускается!");

        menuKey = registerKey("menu", GLFW.GLFW_KEY_RIGHT_SHIFT);
        freeCamKey = registerKey("freecam", GLFW.GLFW_KEY_F6);
        createHueKey = registerKey("createhue", GLFW.GLFW_KEY_H);
        lavaCamKey = registerKey("lavacam", GLFW.GLFW_KEY_L);
        effectsKey = registerKey("effects", GLFW.GLFW_KEY_K);
        glowKey = registerKey("glow", GLFW.GLFW_KEY_P);

        ClientTickEvents.END_CLIENT_TICK.register(client -> {
            if (client.player == null) return;

            if (menuKey.wasPressed()) client.setScreen(new ModMenu());
            if (freeCamKey.wasPressed()) freeCam.toggle();
            if (createHueKey.wasPressed()) {
                createHue.enabled = !createHue.enabled;
                msg(client, "CreateHue: " + onOff(createHue.enabled));
            }
            if (lavaCamKey.wasPressed()) {
                lavaCam.toggle();
                msg(client, "LavaCam: " + onOff(lavaCam.enabled));
            }
            if (effectsKey.wasPressed()) {
                effects.fireworks = !effects.fireworks;
                msg(client, "Effects: " + onOff(effects.fireworks));
            }
            if (glowKey.wasPressed()) {
                glow.enabled = !glow.enabled;
                msg(client, "Glow: " + onOff(glow.enabled));
            }
        });

        LOGGER.info("ModGG Client готов! RSHIFT = меню.");
    }

    private KeyBinding registerKey(String name, int key) {
        return KeyBindingHelper.registerKeyBinding(new KeyBinding(
            "key.modgg." + name, InputUtil.Type.KEYSYM, key, "category.modgg.main"));
    }

    private void msg(net.minecraft.client.MinecraftClient client, String s) {
        client.player.sendMessage(Text.literal(s), true);
    }

    private String onOff(boolean b) { return b ? "§aON" : "§cOFF"; }
}
