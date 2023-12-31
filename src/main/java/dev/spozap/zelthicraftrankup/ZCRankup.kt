package dev.spozap.zelthicraftrankup

import dev.spozap.zelthicraftrankup.commands.PlayedTimeCommand
import dev.spozap.zelthicraftrankup.commands.RanksCommand
import dev.spozap.zelthicraftrankup.commands.RankupCommand
import dev.spozap.zelthicraftrankup.commands.ReloadCommand
import dev.spozap.zelthicraftrankup.hooks.VaultHook
import dev.spozap.zelthicraftrankup.managers.RanksManager
import dev.spozap.zelthicraftrankup.managers.RankupManager
import dev.spozap.zelthicraftrankup.utils.ColorUtils
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

        Bukkit.getConsoleSender().sendMessage(ColorUtils.translate("&3&l[ZCRankup] &aCargado correctamente"))
        Bukkit.getConsoleSender().sendMessage(ColorUtils.translate("&3&l[ZCRankup] &2Hecho por Xx_EvilMelon_xX &4<33"))

        Bukkit.getPluginCommand("rangos")!!.setExecutor(RanksCommand())
        Bukkit.getPluginCommand("rankup")!!.setExecutor(RankupCommand())
        Bukkit.getPluginCommand("tiempojugado")!!.setExecutor(PlayedTimeCommand())
        Bukkit.getPluginCommand("zcreload")!!.setExecutor(ReloadCommand())
    }

    override fun onDisable() {
        // Plugin shutdown logic
        ranksManager.saveRanks()
    }
}
