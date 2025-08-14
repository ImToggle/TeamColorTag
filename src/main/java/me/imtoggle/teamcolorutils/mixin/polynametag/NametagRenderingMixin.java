package me.imtoggle.teamcolorutils.mixin.polynametag;

import me.imtoggle.teamcolorutils.UtilKt;
import org.polyfrost.polynametag.render.NametagRenderingKt;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Pseudo
@Mixin(value = NametagRenderingKt.class, remap = false)
public class NametagRenderingMixin {

    @Dynamic
    @ModifyArgs(method = "drawBackground", at = @At(value = "INVOKE", target = "Lorg/lwjgl/opengl/GL11;glColor4f(FFFF)V"))
    private static void setColor(Args args) {
        if (!UtilKt.renderingPlayer) return;
        args.set(0, UtilKt.bgColor.getRed() / 255f);
        args.set(1, UtilKt.bgColor.getGreen() / 255f);
        args.set(2, UtilKt.bgColor.getBlue() / 255f);
    }
}
