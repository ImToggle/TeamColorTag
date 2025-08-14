package me.imtoggle.teamcolorutils

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Checkbox
import cc.polyfrost.oneconfig.config.annotations.DualOption
import cc.polyfrost.oneconfig.config.annotations.Slider
import cc.polyfrost.oneconfig.config.data.*

object ModConfig : Config(Mod(TeamColorUtils.NAME, ModType.UTIL_QOL), "${TeamColorUtils.MODID}.json") {

    @Checkbox(
        name = "Colored Nametag",
        subcategory = "General"
    )
    var coloredNametag = false

    @Checkbox(
        name = "Colored Hitbox",
        subcategory = "General"
    )
    var coloredHitbox = false

    // Hue

    @DualOption(
        name = "Mode",
        left = "Static", right = "Offset",
        subcategory = "Hue"
    )
    var hueMode = true

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
    var saturationMode = true

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
    var brightnessMode = true

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
        val options = listOf("hueMode", "hueValue", "hueOffset", "saturationMode", "saturationValue", "saturationOffset", "brightnessMode", "brightnessValue", "brightnessOffset")
        for (option in options) {
            addListener(option) {
                updateAllColors()
            }
        }
    }

}