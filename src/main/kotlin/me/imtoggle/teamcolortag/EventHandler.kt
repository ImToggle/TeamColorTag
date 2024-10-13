package me.imtoggle.teamcolortag

import cc.polyfrost.oneconfig.config.core.OneColor
import me.imtoggle.teamcolortag.hook.ScorePlayerTeamHook
import net.minecraft.entity.EntityLivingBase
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.scoreboard.ScorePlayerTeam
import net.minecraftforge.client.event.RenderLivingEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object EventHandler {

    fun initialize() {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun preRender(event: RenderLivingEvent.Specials.Pre<EntityLivingBase>) {
        if (event.entity !is EntityPlayer) return
        event.entity.team ?: return
        renderingPlayer = true
        val color = (event.entity.team as ScorePlayerTeam as ScorePlayerTeamHook).`teamColorTag$getTeamColor`()
        bgColor = OneColor(getHue(color.hue), getSaturation(color.saturation), getBrightness(color.brightness), 255, -1)
    }

    @SubscribeEvent
    fun postRender(event: RenderLivingEvent.Specials.Post<EntityLivingBase>) {
        renderingPlayer = false
    }
}