package dev.spozap.zelthicraftrankup.hooks

import net.milkbowl.vault.economy.Economy
import net.milkbowl.vault.permission.Permission
import org.bukkit.Bukkit
import org.bukkit.plugin.RegisteredServiceProvider

class VaultHook {

    companion object {
        lateinit var economy: Economy
        lateinit var permissions: Permission

        init {
            if (Bukkit.getPluginManager().getPlugin("Vault") != null) {
                setupPermissions()
                setupEconomy()
            }
        }

        private fun setupEconomy(): Boolean {
            val rsp: RegisteredServiceProvider<Economy>? = Bukkit.getServicesManager().getRegistration(Economy::class.java)

            if (rsp == null) {
                throw IllegalStateException("Economy service not found")
            }

            economy = rsp.provider
            return true
        }

        private fun setupPermissions(): Boolean {
            val rsp: RegisteredServiceProvider<Permission>? = Bukkit.getServicesManager().getRegistration(Permission::class.java)

            if (rsp == null) {
                throw IllegalStateException("Permissions service not found")
            }

            permissions = rsp.provider
            return true
        }
    }

}
