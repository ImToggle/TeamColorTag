package me.imtoggle.teamcolortag

import cc.polyfrost.oneconfig.config.core.OneColor

@JvmField
var renderingPlayer = false

@JvmField
var bgColor = OneColor(0)

fun getHue(hue: Int): Int {
    if (ModConfig.hueMode) {
        var value = hue + ModConfig.hueOffset
        if (value > 360) {
            value -= 360
        } else if (value < 360) {
            value += 360
        }
        return value
    } else {
        return ModConfig.hueValue
    }
}

fun getSaturation(saturation: Int): Int {
    return if (ModConfig.saturationMode) (saturation + ModConfig.saturationOffset).coerceIn(0..100) else ModConfig.saturationValue
}

fun getBrightness(brightness: Int): Int {
    return if (ModConfig.brightnessMode) (brightness + ModConfig.brightnessOffset).coerceIn(0..100) else ModConfig.brightnessValue
}