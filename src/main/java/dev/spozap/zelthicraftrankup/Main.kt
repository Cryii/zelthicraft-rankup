package dev.spozap.zelthicraftrankup

import dev.spozap.zelthicraftrankup.commands.RanksCommand
import dev.spozap.zelthicraftrankup.managers.RankupManager
import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    private lateinit var rankupManager: RankupManager

    companion object {
        lateinit var luckPermsAPI : LuckPerms
        lateinit var plugin : Main
    }

    override fun onEnable() {

        plugin = this

        saveDefaultConfig()

        val luckPermsProvider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)

        if (luckPermsProvider == null) {
            this.pluginLoader.disablePlugin(this)
            return
        }

        luckPermsAPI = luckPermsProvider.provider


        rankupManager = RankupManager()

        Bukkit.getPluginCommand("rangos")!!.setExecutor(RanksCommand())

    }

    override fun onDisable() {
        // Plugin shutdown logic
        rankupManager.saveRanks()
    }
}
