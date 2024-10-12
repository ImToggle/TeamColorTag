package me.imtoggle.teamcolortag.mixin.vanilla;

import me.imtoggle.teamcolortag.hook.ScorePlayerTeamHook;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.awt.*;

@Mixin(ScorePlayerTeam.class)
public class ScorePlayerTeamMixin implements ScorePlayerTeamHook {

    @Unique
    private Color teamColorTag$teamColor = new Color(0x00000000);

    @Override
    public Color teamColorTag$getTeamColor() {
        return teamColorTag$teamColor;
    }

    @Inject(method = "setNamePrefix", at = @At(value = "INVOKE", target = "Lnet/minecraft/scoreboard/Scoreboard;sendTeamUpdate(Lnet/minecraft/scoreboard/ScorePlayerTeam;)V"))
    private void updateColor(String prefix, CallbackInfo ci) {
        String s = FontRenderer.getFormatFromString(prefix);
        if (s.length() >= 2) {
            teamColorTag$teamColor = new Color(Minecraft.getMinecraft().fontRendererObj.getColorCode(s.charAt(1)));
        }
    }
}