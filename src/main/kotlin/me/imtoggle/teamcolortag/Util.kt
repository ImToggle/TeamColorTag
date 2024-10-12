package me.imtoggle.teamcolortag

import me.imtoggle.teamcolortag.hook.ScorePlayerTeamHook
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.scoreboard.ScorePlayerTeam
import java.awt.Color

@JvmField
var renderingPlayer = false

@JvmField
var bgColor = Color(0)

fun preRender(player: AbstractClientPlayer) {
    player.team ?: return
    renderingPlayer = true
    bgColor = (player.team as ScorePlayerTeam as ScorePlayerTeamHook).`teamColorTag$getTeamColor`()
}

fun postRender() {
    renderingPlayer = false
}