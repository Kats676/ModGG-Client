package com.modgg.client.mixin;

import net.minecraft.client.MinecraftClient;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.sound.SoundEvents;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(AbstractClientPlayerEntity.class)
public class PlayerDeathMixin {

    // Включение/выключение эффектов
    public static boolean fireworks = true; 
    public static boolean totemEffect = true; // Переключатель для эффекта тотема

    @Inject(method = "setHealth", at = @At("HEAD"))
    private void onSetHealth(float health, CallbackInfo ci) {
        if (!fireworks && !totemEffect) return;

        AbstractClientPlayerEntity victim = (AbstractClientPlayerEntity) (Object) this;
        var client = MinecraftClient.getInstance();
        var world = client.world;

        if (world == null || client.player == null) return;

        // Проверяем момент фактической смерти чужого игрока
        if (health <= 0.0F && !victim.isDead() && victim != client.player) {
            
            // 1. ЛОГИКА ФЕЙЕРВЕРКА
            if (fireworks) {
                for (int i = 0; i < 50; i++) {
                    world.addParticle(ParticleTypes.FIREWORK,
                        victim.getX() + (Math.random() - 0.5) * 2,
                        victim.getY() + Math.random() * 2,
                        victim.getZ() + (Math.random() - 0.5) * 2,
                        0, 0, 0);
                }
                world.playSound(null, victim.getBlockPos(), SoundEvents.ENTITY_FIREWORK_ROCKET_BLAST,
                    victim.getSoundCategory(), 1.0F, 1.0F);
            }

            // 2. ЛОГИКА ТОТЕМОВ
            if (totemEffect) {
                // Спавним зеленые магические частицы тотема
                for (int i = 0; i < 40; i++) {
                    world.addParticle(ParticleTypes.TOTEM_OF_UNDYING,
                        victim.getX() + (Math.random() - 0.5) * 1.5,
                        victim.getY() + 0.5 + Math.random() * 1.5,
                        victim.getZ() + (Math.random() - 0.5) * 1.5,
                        (Math.random() - 0.5) * 0.2, 
                        Math.random() * 0.2, 
                        (Math.random() - 0.5) * 0.2
                    );
                }
                // Проигрываем звук использования тотема
                world.playSound(null, victim.getBlockPos(), SoundEvents.ITEM_TOTEM_USE,
                    victim.getSoundCategory(), 1.0F, 1.0F);
            }
        }
    }
}
