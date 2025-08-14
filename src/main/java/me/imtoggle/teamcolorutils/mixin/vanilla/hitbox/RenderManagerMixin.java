package me.imtoggle.teamcolorutils.mixin.vanilla.hitbox;

import me.imtoggle.teamcolorutils.ModConfig;
import me.imtoggle.teamcolorutils.UtilKt;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.entity.Entity;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

@Mixin(RenderManager.class)
public class RenderManagerMixin {

    @Inject(method = "renderDebugBoundingBox", at = @At(value = "INVOKE", target = "Lnet/minecraft/client/renderer/RenderGlobal;drawOutlinedBoundingBox(Lnet/minecraft/util/AxisAlignedBB;IIII)V", ordinal = 0))
    private void start(Entity entityIn, double x, double y, double z, float entityYaw, float partialTicks, CallbackInfo ci) {
        UtilKt.currentHitboxEntity = entityIn;
        UtilKt.renderingHitbox = ModConfig.INSTANCE.getColoredHitbox();
    }

}