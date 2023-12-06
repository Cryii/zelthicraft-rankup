package dev.spozap.zelthicraftrankup

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import org.bukkit.plugin.java.JavaPlugin

class Main : JavaPlugin() {

    private lateinit var luckPermsAPI : LuckPerms

    override fun onEnable() {
        println("Se ha iniciado el plugin cacaota")
        val luckPermsProvider = Bukkit.getServicesManager().getRegistration(LuckPerms::class.java)
        if (luckPermsProvider == null) {
            this.pluginLoader.disablePlugin(this)
            return
        }

        luckPermsAPI = luckPermsProvider.provider
    }

    override fun onDisable() {
        // Plugin shutdown logic
    }
}
