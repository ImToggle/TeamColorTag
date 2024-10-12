package me.imtoggle.teamcolortag

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.data.*

object ModConfig : Config(Mod(TeamColorTag.NAME, ModType.UTIL_QOL), "${TeamColorTag.MODID}.json") {

    @Slider(
        name = "Brightness (%)",
        min = 0f, max = 100f
    )
    var brightness = 100

}