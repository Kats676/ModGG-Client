package com.modgg.client.mixin;

import com.modgg.client.modules.LavaCam;
import net.minecraft.client.render.BackgroundRenderer;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(BackgroundRenderer.class)
public class LavaCamMixin {

    @Inject(
        method = "applyFog", 
        at = @At("HEAD"), 
        cancellable = true
    )
    private static void onApplyFog(Camera camera, BackgroundRenderer.FogType fogType, float viewDistance, boolean thickFog, float tickDelta, CallbackInfo ci) {
        // Если модуль LavaCam включен, и камера находится внутри лавы
        if (LavaCam.enabled && camera.getSubmersionType() == net.minecraft.block.enums.CameraSubmersionType.LAVA) {
            // Отменяем стандартный густой туман лавы, делая обзор чистым
            ci.cancel();
        }
    }
}
