package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.managers.RanksManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RanksCommand(private val ranksManager: RanksManager) : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {
        val player = sender as Player
        ranksManager.showRanks(player)
        return true
    }

}