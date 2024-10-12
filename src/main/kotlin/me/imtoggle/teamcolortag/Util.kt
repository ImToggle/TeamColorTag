package me.imtoggle.teamcolortag

import me.imtoggle.teamcolortag.hook.ScorePlayerTeamHook
import net.minecraft.client.entity.AbstractClientPlayer
import net.minecraft.scoreboard.ScorePlayerTeam

@JvmField
var renderingPlayer = false

@JvmField
var bgColor = 0

fun preRender(player: AbstractClientPlayer) {
    renderingPlayer = true
    player.team ?: return
    bgColor = (player.team as ScorePlayerTeam as ScorePlayerTeamHook).`teamColorTag$getTeamColor`()
}

fun postRender() {
    renderingPlayer = false
}