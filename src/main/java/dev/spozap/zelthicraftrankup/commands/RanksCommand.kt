package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.constants.Permissions
import dev.spozap.zelthicraftrankup.managers.RanksManager
import org.bukkit.Statistic
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RanksCommand(private val ranksManager: RanksManager = ZCRankup.ranksManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return true

        val player = sender

        if (!player.hasPermission(Permissions.RANKS_COMMAND)) {
            player.sendMessage("No tienes permisos para ejecutar este comando")
            return true
        }

        ranksManager.showRanks(player)
        return true
    }

}