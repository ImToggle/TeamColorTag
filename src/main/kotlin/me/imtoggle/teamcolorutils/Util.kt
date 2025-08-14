package me.imtoggle.teamcolorutils

import cc.polyfrost.oneconfig.config.core.OneColor
import me.imtoggle.teamcolorutils.hook.ScorePlayerTeamHook
import net.minecraft.entity.Entity
import net.minecraft.entity.player.EntityPlayer
import net.minecraft.scoreboard.ScorePlayerTeam

@JvmField
var renderingPlayer = false

@JvmField
var renderingHitbox = false

@JvmField
var currentHitboxEntity: Entity? = null

@JvmField
var bgColor = OneColor(0)

private val mcColorCodes = IntArray(16)
var modifiedColorCodes = ArrayList<OneColor>()

fun fillColor() {
    for (i in 0..15) {
        val j = (i shr 3 and 1) * 85
        var k = (i shr 2 and 1) * 170 + j
        val l = (i shr 1 and 1) * 170 + j
        val i1 = (i shr 0 and 1) * 170 + j

        if (i == 6) {
            k += 85
        }
        mcColorCodes[i] = (k and 255) shl 16 or ((l and 255) shl 8) or (i1 and 255)
    }
    updateAllColors()
}

fun updateAllColors() {
    modifiedColorCodes.clear()
    for (i in 0..15) {
        val hsba = OneColor.ARGBtoHSBA(mcColorCodes[i])
        modifiedColorCodes.add(OneColor(getHue(hsba[0]), getSaturation(hsba[1]), getBrightness(hsba[2]), 255, -1))
    }
}

fun getHue(hue: Short): Int {
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

fun getSaturation(saturation: Short): Int {
    return if (ModConfig.saturationMode) (saturation + ModConfig.saturationOffset).coerceIn(0..100) else ModConfig.saturationValue
}

fun getBrightness(brightness: Short): Int {
    return if (ModConfig.brightnessMode) (brightness + ModConfig.brightnessOffset).coerceIn(0..100) else ModConfig.brightnessValue
}

fun Entity.getTeamColor(): OneColor? {
    if (!ModConfig.enabled) return null
    if (this !is EntityPlayer) return null
    this.team ?: return null
    val char = (this.team as ScorePlayerTeam as ScorePlayerTeamHook).`teamColorTag$getTeamColor`()
    return modifiedColorCodes["0123456789abcdef".indexOf(char)]
}