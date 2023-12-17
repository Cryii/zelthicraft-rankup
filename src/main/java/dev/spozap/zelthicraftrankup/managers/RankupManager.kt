package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.Main
import dev.spozap.zelthicraftrankup.models.Rankup

class RankupManager(val plugin : Main = Main.plugin) {

    fun isRankupPossible(rankup: Rankup) : Boolean {
        if (rankup.to == null) return  false
        val rankRequirements = rankup.to.requirements
        println("requirements size ${rankRequirements.size}")
        return rankRequirements.all { requirement -> requirement.isMet(rankup.player) }
    }

}