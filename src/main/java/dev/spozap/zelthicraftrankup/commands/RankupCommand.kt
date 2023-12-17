package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.models.Rankup
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RankupCommand : CommandExecutor {

    private var ranksManager = ZCRankup.ranksManager
    private var rankupManager = ZCRankup.rankupManager

    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return true

        if (!ranksManager.hasRankupRank(player = sender)) {
            sender.sendMessage("No tienes ningun rango disponible para hacer rankup!")
            return true
        }

        if (ranksManager.hasMaximumRank(sender)) {
            sender.sendMessage("Ya tienes el rango maximo")
            return true
        }

        val rank = ranksManager.getFirstRankupPlayerRank(sender)
        val nextRank = ranksManager.getNextRank(rank.id)

        val rankup = Rankup(rank, nextRank, sender)

        val possible = rankupManager.isRankupPossible(rankup)

        if (!possible) {
            sender.sendMessage("No cumples los requerimientos para hacer el rankup")
            return true
        }

        rankupManager.rankup(rankup)
        return true
    }
}