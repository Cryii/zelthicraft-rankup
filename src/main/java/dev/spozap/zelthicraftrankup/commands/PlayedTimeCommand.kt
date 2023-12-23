package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.constants.Permissions
import dev.spozap.zelthicraftrankup.utils.DateUtils
import org.bukkit.Statistic
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class PlayedTimeCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        if (sender !is Player) return true

        val player = sender

        if (!player.hasPermission(Permissions.PLAYED_TIME_COMMAND)) {
            player.sendMessage("No tienes permisos para ejecutar este comando")
            return true
        }

        if(args.isNotEmpty()) return true

        val playedTime = System.currentTimeMillis() - (player.getStatistic(Statistic.PLAY_ONE_MINUTE) * 50L)

        player.sendMessage(DateUtils.formatDate(playedTime))
        return true
    }
}