package me.imtoggle.teamcolortag

import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(modid = TeamColorTag.MODID, name = TeamColorTag.NAME, version = TeamColorTag.VERSION, modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter")
object TeamColorTag {
    const val MODID = "@ID@"
    const val NAME = "@NAME@"
    const val VERSION = "@VER@"

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent) {
        ModConfig
        EventHandler.initialize()
    }
}