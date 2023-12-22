package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.ZCRankup
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReloadCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {
        ZCRankup.ranksManager.reload()
        return true
    }
}