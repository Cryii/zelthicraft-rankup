package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.constants.Permissions
import dev.spozap.zelthicraftrankup.managers.ConfigManager
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

        val player = sender

        if (!player.hasPermission(Permissions.RANKUP_COMMAND)) {
            player.sendMessage(ConfigManager.NO_PERMISSIONS)
            return true
        }

        if (!ranksManager.hasRankupRank(player = sender)) {
            player.sendMessage(ConfigManager.NO_RANKUP_AVAILABLE)
            return true
        }

        if (ranksManager.hasMaximumRank(sender)) {
            player.sendMessage(ConfigManager.MAXIMUM_RANK)
            return true
        }

        val rank = ranksManager.getFirstRankupPlayerRank(sender)
        val nextRank = ranksManager.getNextRank(rank.id)

        val rankup = Rankup(rank, nextRank, sender)

        val possible = rankupManager.isRankupPossible(rankup)

        if (!possible) {
            player.sendMessage(ConfigManager.REQUISITES_NOT_MEET)
            return true
        }

        rankupManager.rankup(rankup)
        player.sendMessage(ConfigManager.RANKUP_SUCCESS)

        return true
    }
}