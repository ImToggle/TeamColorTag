package me.imtoggle.teamcolortag.mixin;

import me.imtoggle.teamcolortag.UtilKt;
import net.minecraft.client.renderer.entity.Render;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Mixin(Render.class)
public class RenderMixin {

    @ModifyArgs(method = "renderLivingLabel", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/WorldRenderer;color(FFFF)Lnet/minecraft/client/renderer/WorldRenderer;"))
    private void setColor(Args args) {
        if (!UtilKt.renderingPlayer) return;
        args.set(0, UtilKt.bgColor.getRed() / 255f);
        args.set(1, UtilKt.bgColor.getGreen() / 255f);
        args.set(2, UtilKt.bgColor.getBlue() / 255f);
    }
}
