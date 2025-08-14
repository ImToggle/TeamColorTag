package me.imtoggle.teamcolorutils

import net.minecraft.entity.EntityLivingBase
import net.minecraftforge.client.event.RenderLivingEvent
import net.minecraftforge.common.MinecraftForge
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent

object EventHandler {

    fun initialize() {
        MinecraftForge.EVENT_BUS.register(this)
    }

    @SubscribeEvent
    fun preRender(event: RenderLivingEvent.Specials.Pre<EntityLivingBase>) {
        event.entity.getTeamColor()?.let {
            renderingPlayer = ModConfig.coloredNametag
            bgColor = it
        }
    }

    @SubscribeEvent
    fun postRender(event: RenderLivingEvent.Specials.Post<EntityLivingBase>) {
        if (!renderingPlayer) return
        renderingPlayer = false
    }
}