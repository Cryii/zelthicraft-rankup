package dev.spozap.zelthicraftrankup.models

import dev.spozap.zelthicraftrankup.utils.DateUtils
import org.bukkit.Statistic
import org.bukkit.entity.Player

class TimeRankupRequirement(private var rankTime: Long) : RankupRequirement() {

    override fun isMet(player: Player): Boolean {
        val playedTime = System.currentTimeMillis() - (player.getStatistic(Statistic.PLAY_ONE_MINUTE) * 50L)
        return DateUtils.toMinutes(playedTime) >= rankTime
    }

    override fun execute(player: Player) {
        return
    }
}