package dev.chromium.client.mixin;

import dev.chromium.client.ChromiumClient;
import dev.chromium.client.module.VisualModules.Fullbright;
import net.minecraft.client.render.LightmapTextureManager;
import net.minecraft.world.dimension.DimensionType;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(LightmapTextureManager.class)
public class LightmapTextureManagerMixin {
    @Inject(method = "getBrightness(Lnet/minecraft/world/dimension/DimensionType;I)F", at = @At("RETURN"), cancellable = true, require = 0)
    private static void chromium$fullbrightDimension(DimensionType type, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (ChromiumClient.on(Fullbright.class)) cir.setReturnValue(1.0f);
    }

    @Inject(method = "getBrightness(FI)F", at = @At("RETURN"), cancellable = true, require = 0)
    private static void chromium$fullbrightAmbient(float ambientLight, int lightLevel, CallbackInfoReturnable<Float> cir) {
        if (ChromiumClient.on(Fullbright.class)) cir.setReturnValue(1.0f);
    }
}
