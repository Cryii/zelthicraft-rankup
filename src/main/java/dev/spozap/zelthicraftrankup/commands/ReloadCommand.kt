package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.constants.Permissions
import dev.spozap.zelthicraftrankup.managers.ConfigManager
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender

class ReloadCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>?): Boolean {

        if (!sender.hasPermission(Permissions.RELOAD_COMMAND)) {
            sender.sendMessage("No tienes permisos para ejecutar este comando")
            return true
        }

        sender.sendMessage("ZCRankup recargado correctamente")

        ZCRankup.ranksManager.reload()
        ConfigManager.reloadConfig()

        return true
    }
}