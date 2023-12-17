package dev.spozap.zelthicraftrankup.models

import dev.spozap.zelthicraftrankup.Main
import org.bukkit.Bukkit
import org.bukkit.entity.Player

class MoneyRankupRequirement(val requiredRankupMoney: Double) : RankupRequirement() {

    init {
        println("Se carga moneyRankupReq con $requiredRankupMoney")
    }

    override fun isMet(player: Player): Boolean {

        val offlinePlayer = Bukkit.getOfflinePlayer(player.uniqueId)
        val playerBalance = Main.economyApi.getBalance(offlinePlayer)

        println("Tiene $playerBalance")

        return playerBalance >= requiredRankupMoney
    }

    override fun execute(player: Player) {
        val offlinePlayer = Bukkit.getOfflinePlayer(player.uniqueId)
        val response = Main.economyApi.withdrawPlayer(offlinePlayer, requiredRankupMoney)

        if (response.transactionSuccess()) {
            player.sendMessage("- $requiredRankupMoney$")
        }

    }
}