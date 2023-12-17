package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.Main
import dev.spozap.zelthicraftrankup.models.Rankup
import dev.spozap.zelthicraftrankup.models.RankupRequirement
import org.bukkit.entity.Player

class RankupManager(val plugin : Main = Main.plugin) {

    fun isRankupPossible(rankup: Rankup) : Boolean {
        if (rankup.to == null) return  false
        val rankRequirements = rankup.to.requirements
        println("requirements size ${rankRequirements.size}")
        return rankRequirements.all { requirement -> requirement.isMet(rankup.player) }
    }

    fun rankup(rankup: Rankup) {

        val requirements = rankup.to!!.requirements
        executeRankupRequirements(requirements, rankup.player)

        Main.permissionsApi.playerAddGroup(null, rankup.player, rankup.to.id)
        Main.permissionsApi.playerRemoveGroup(null, rankup.player, rankup.from.id)

    }

    fun executeRankupRequirements(requirements: List<RankupRequirement>, player: Player) {
        requirements.forEach { requirement -> requirement.execute(player)}
    }

}