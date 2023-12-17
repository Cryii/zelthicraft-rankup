package dev.spozap.zelthicraftrankup

import dev.spozap.zelthicraftrankup.commands.RanksCommand
import dev.spozap.zelthicraftrankup.commands.RankupCommand
import dev.spozap.zelthicraftrankup.hooks.VaultHook
import dev.spozap.zelthicraftrankup.listeners.PlayerListeners
import dev.spozap.zelthicraftrankup.managers.RanksManager
import dev.spozap.zelthicraftrankup.managers.RankupManager
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class ZCRankup : JavaPlugin() {

    companion object {
        lateinit var plugin : ZCRankup
        lateinit var ranksManager: RanksManager
        lateinit var rankupManager: RankupManager
        lateinit var permissionsApi : Permission
        lateinit var economyApi : Economy
    }

    override fun onEnable() {

        plugin = this

        permissionsApi = VaultHook.permissions
        economyApi = VaultHook.economy

        ranksManager = RanksManager()
        rankupManager = RankupManager()

        saveDefaultConfig()

        server.pluginManager.registerEvents(PlayerListeners(), this)

        Bukkit.getPluginCommand("rangos")!!.setExecutor(RanksCommand(ranksManager))
        Bukkit.getPluginCommand("rankup")!!.setExecutor(RankupCommand())

    }

    override fun onDisable() {
        // Plugin shutdown logic
        ranksManager.saveRanks()
    }
}
