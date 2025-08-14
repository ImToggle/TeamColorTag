package me.imtoggle.teamcolorutils.mixin.polyhitbox;

import cc.polyfrost.oneconfig.config.core.OneColor;
import me.imtoggle.teamcolorutils.ModConfig;
import me.imtoggle.teamcolorutils.UtilKt;
import net.minecraft.entity.Entity;
import net.minecraft.util.AxisAlignedBB;
import org.polyfrost.polyhitbox.config.HitboxConfig;
import org.polyfrost.polyhitbox.render.HitboxRenderer;
import org.spongepowered.asm.mixin.Dynamic;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Pseudo;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.ModifyArgs;
import org.spongepowered.asm.mixin.injection.Redirect;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.invoke.arg.Args;

@Pseudo
@Mixin(value = HitboxRenderer.class, remap = false)
public class HitboxRendererMixin {

    @Shadow
    private void drawBoxOutline(HitboxConfig config, AxisAlignedBB hitbox, OneColor color, float thickness) {
    }

    @Dynamic
    @Inject(method = "renderHitbox", at = @At("HEAD"))
    private void captureEntity(HitboxConfig config, Entity entity, double x, double y, double z, float partialTicks, CallbackInfo ci) {
        UtilKt.currentHitboxEntity = entity;
    }

    @Dynamic
    @Redirect(method = "renderHitbox", at = @At(value = "INVOKE", target = "Lorg/polyfrost/polyhitbox/render/HitboxRenderer;drawBoxOutline(Lorg/polyfrost/polyhitbox/config/HitboxConfig;Lnet/minecraft/util/AxisAlignedBB;Lcc/polyfrost/oneconfig/config/core/OneColor;F)V"))
    private void replaceOutlineColor(HitboxRenderer instance, HitboxConfig config, AxisAlignedBB hitbox, OneColor color, float thickness) {
        if (!ModConfig.INSTANCE.getColoredHitbox() || !config.getOutlineColor().equals(color) || UtilKt.currentHitboxEntity == null) {
            drawBoxOutline(config, hitbox, color, thickness);
        } else {
            OneColor teamColor = UtilKt.getTeamColor(UtilKt.currentHitboxEntity);
            if (teamColor != null) {
                teamColor.setAlpha(color.getAlpha());
            }
            drawBoxOutline(config, hitbox, teamColor == null ? color : teamColor, thickness);
        }
    }

    @ModifyArgs(method = "drawSide", at = @At(value = "INVOKE", target = "Lorg/polyfrost/polyhitbox/render/HitboxRenderer;glColor(Lcc/polyfrost/oneconfig/config/core/OneColor;)V"))
    private void replaceSideColor(Args args) {
        if (!ModConfig.INSTANCE.getColoredHitbox() || UtilKt.currentHitboxEntity == null) return;
        OneColor teamColor = UtilKt.getTeamColor(UtilKt.currentHitboxEntity);
        if (teamColor == null) return;
        teamColor.setAlpha(((OneColor) args.get(0)).getAlpha());
        args.set(0, teamColor);
    }
}