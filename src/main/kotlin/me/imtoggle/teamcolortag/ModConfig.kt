package me.imtoggle.teamcolortag

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.DualOption
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.data.*

object ModConfig : Config(Mod(TeamColorTag.NAME, ModType.UTIL_QOL), "${TeamColorTag.MODID}.json") {

    // Hue

    @DualOption(
        name = "Mode",
        left = "Static", right = "Offset",
        subcategory = "Hue"
    )
    var hueMode = false

    @Slider(
        name = "Value",
        min = 0f, max = 360f,
        subcategory = "Hue"
    )
    var hueValue = 0

    @Slider(
        name = "Offset",
        min = -180f, max = 180f,
        subcategory = "Hue"
    )
    var hueOffset = 0

    // Saturation

    @DualOption(
        name = "Mode",
        left = "Static", right = "Offset",
        subcategory = "Saturation"
    )
    var saturationMode = false

    @Slider(
        name = "Value",
        min = 0f, max = 100f,
        subcategory = "Saturation"
    )
    var saturationValue = 50

    @Slider(
        name = "Offset",
        min = -100f, max = 100f,
        subcategory = "Saturation"
    )
    var saturationOffset = 0

    // Brightness

    @DualOption(
        name = "Mode",
        left = "Static", right = "Offset",
        subcategory = "Brightness"
    )
    var brightnessMode = false

    @Slider(
        name = "Value",
        min = 0f, max = 100f,
        subcategory = "Brightness"
    )
    var brightnessValue = 50

    @Slider(
        name = "Offset",
        min = -100f, max = 100f,
        subcategory = "Brightness"
    )
    var brightnessOffset = 0

    init {
        initialize()
        hideIf("hueValue") { hueMode }
        hideIf("hueOffset") { !hueMode }
        hideIf("saturationValue") { saturationMode }
        hideIf("saturationOffset") { !saturationMode }
        hideIf("brightnessValue") { brightnessMode }
        hideIf("brightnessOffset") { !brightnessMode }
    }

}