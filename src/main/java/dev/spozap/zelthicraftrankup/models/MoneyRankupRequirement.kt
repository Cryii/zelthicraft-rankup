package dev.spozap.zelthicraftrankup.models

import dev.spozap.zelthicraftrankup.ZCRankup
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class MoneyRankupRequirement(val requiredRankupMoney: Double) : RankupRequirement() {

    override fun isMet(player: Player): Boolean {

        val offlinePlayer = Bukkit.getOfflinePlayer(player.uniqueId)
        val playerBalance = ZCRankup.economyApi.getBalance(offlinePlayer)

        return playerBalance >= requiredRankupMoney
    }

    override fun execute(player: Player) {
        val offlinePlayer = Bukkit.getOfflinePlayer(player.uniqueId)
        val response = ZCRankup.economyApi.withdrawPlayer(offlinePlayer, requiredRankupMoney)

        if (response.transactionSuccess()) {
            player.sendMessage("- $requiredRankupMoney$")
        }

    }
}