package com.modgg.client.mixin;

import com.modgg.client.ModGGClient;
import net.minecraft.client.render.Camera;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(Camera.class)
public class FreeCamMixin {

    @Inject(method = "update", at = @At("TAIL"))
    private void onCameraUpdate(net.minecraft.world.BlockView area, net.minecraft.entity.Entity focusedEntity, boolean thirdPerson, boolean inverseView, float tickDelta, CallbackInfo ci) {
        if (ModGGClient.freeCam.enabled) {
            Camera camera = (Camera) (Object) this;
            ((CameraAccessor) camera).setPos(new net.minecraft.util.math.Vec3d(
                ModGGClient.freeCam.fakeX, 
                ModGGClient.freeCam.fakeY, 
                ModGGClient.freeCam.fakeZ
            ));
        }
    }
}
