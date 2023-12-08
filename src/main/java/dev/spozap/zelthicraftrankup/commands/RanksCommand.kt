package dev.spozap.zelthicraftrankup.commands

import dev.spozap.zelthicraftrankup.Main
import org.bukkit.command.Command
import org.bukkit.command.CommandExecutor
import org.bukkit.command.CommandSender
import org.bukkit.entity.Player

class RanksCommand : CommandExecutor {
    override fun onCommand(sender: CommandSender, command: Command, label: String, args: Array<out String>): Boolean {

        val player = sender as Player

        val user = Main.luckPermsAPI.userManager.getUser(player.uniqueId)

        user.let {
            it?.getInheritedGroups(user!!.queryOptions)?.map {
                group -> player.sendMessage("Tienes rango: ${group.name}")
            }
        }

        return true
    }

}