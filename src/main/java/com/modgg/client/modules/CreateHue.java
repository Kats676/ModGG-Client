package com.modgg.client.mixin;

import net.minecraft.client.render.item.HeldItemRenderer;
import net.minecraft.client.util.math.MatrixStack;
import net.minecraft.client.network.AbstractClientPlayerEntity;
import net.minecraft.util.Hand;
import net.minecraft.item.ItemStack;
import net.minecraft.util.math.RotationAxis;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(HeldItemRenderer.class)
public class HeldItemRendererMixin {

    // НАСТРОЙКИ РУК ПРЯМО ТУТ:
    public static boolean enabled = true; // Сделайте true, чтобы включить изменения в игре
    public static float scale = 0.7f;      // Размер рук (0.7f — сделать чуть меньше)
    
    // Смещение рук (влево/вправо, вверх/вниз, ближе/дальше)
    public static float offX = 0.2f;       
    public static float offY = -0.1f;
    public static float offZ = -0.2f;
    
    // Вращение рук (в градусах)
    public static float rotX = 10f;
    public static float rotY = 0f;
    public static float rotZ = 0f;

    @Inject(
        method = "renderFirstPersonItem",
        at = @At(
            value = "INVOKE",
            target = "Lnet/minecraft/client/render/item/HeldItemRenderer;renderItem(Lnet/minecraft/entity/LivingEntity;Lnet/minecraft/item/ItemStack;Lnet/minecraft/client/render/model/json/ModelTransformationMode;ZLnet/minecraft/client/util/math/MatrixStack;Lnet/minecraft/client/render/VertexConsumerProvider;I)V"
        )
    )
    private void onRenderFirstPersonItem(
        AbstractClientPlayerEntity player, float tickDelta, float pitch, 
        Hand hand, float swingProgress, ItemStack item, float equipProgress, 
        MatrixStack matrices, net.minecraft.client.render.VertexConsumerProvider vertexConsumers, 
        int light, CallbackInfo ci
    ) {
        // Если функция выключена — ничего не меняем
        if (!enabled) return;

        // Применяем настройки к матрице отрисовки рук
        matrices.scale(scale, scale, scale);
        matrices.translate(offX, offY, offZ);
        matrices.multiply(RotationAxis.POSITIVE_X.rotationDegrees(rotX));
        matrices.multiply(RotationAxis.POSITIVE_Y.rotationDegrees(rotY));
        matrices.multiply(RotationAxis.POSITIVE_Z.rotationDegrees(rotZ));
    }
}
