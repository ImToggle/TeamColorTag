package me.imtoggle.teamcolortag

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.data.*

object ModConfig : Config(Mod(TeamColorTag.NAME, ModType.UTIL_QOL), "${TeamColorTag.MODID}.json") {

    init {
        initialize()
    }
}