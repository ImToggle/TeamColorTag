package me.imtoggle.teamcolorutils.mixin.vanilla.hitbox;

import cc.polyfrost.oneconfig.config.core.OneColor;
import me.imtoggle.teamcolorutils.UtilKt;
import net.minecraft.client.renderer.RenderGlobal;
import net.minecraft.util.AxisAlignedBB;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderGlobal.class)
public abstract class RenderGlobalMixin {

    @Shadow
    public static void drawOutlinedBoundingBox(AxisAlignedBB boundingBox, int red, int green, int blue, int alpha) {
    }

    @Inject(method = "drawOutlinedBoundingBox", at = @At("HEAD"), cancellable = true)
    private static void applyColor(AxisAlignedBB boundingBox, int red, int green, int blue, int alpha, CallbackInfo ci) {
        if (!UtilKt.renderingHitbox) return;
        UtilKt.renderingHitbox = false;
        if (UtilKt.currentHitboxEntity == null) return;
        OneColor color = UtilKt.getTeamColor(UtilKt.currentHitboxEntity);
        if (color == null) return;
        drawOutlinedBoundingBox(boundingBox, color.getRed(), color.getGreen(), color.getBlue(), 255);
        ci.cancel();
    }

}