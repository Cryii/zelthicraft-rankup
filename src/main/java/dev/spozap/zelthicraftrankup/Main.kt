package dev.spozap.zelthicraftrankup

import dev.spozap.zelthicraftrankup.commands.RanksCommand
import dev.spozap.zelthicraftrankup.hooks.VaultHook
import dev.spozap.zelthicraftrankup.managers.RankupManager
import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    private lateinit var rankupManager: RankupManager

    companion object {
        lateinit var plugin : Main

        lateinit var permissionsApi : Permission
        lateinit var economyApi : Economy

    }

    override fun onEnable() {

        plugin = this

        permissionsApi = VaultHook.permissions
        economyApi = VaultHook.economy

        saveDefaultConfig()


        rankupManager = RankupManager()

        Bukkit.getPluginCommand("rangos")!!.setExecutor(RanksCommand(rankupManager))

    }

    override fun onDisable() {
        // Plugin shutdown logic
        rankupManager.saveRanks()
    }
}
