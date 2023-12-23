package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.constants.ConfigMessageKeys
import dev.spozap.zelthicraftrankup.utils.ColorUtils
import org.bukkit.configuration.file.FileConfiguration

class ConfigManager {

    companion object {

        private val config : FileConfiguration = ZCRankup.plugin.config


        val PREFIX = load(ConfigMessageKeys.PREFIX)
        val NO_PERMISSIONS = loadWithPrefix(ConfigMessageKeys.NO_PERMISSIONS)
        val NO_RANKUP_AVAILABLE = loadWithPrefix(ConfigMessageKeys.NO_RANKUP_AVAILABLE)
        val MAXIMUM_RANK = loadWithPrefix(ConfigMessageKeys.MAXIMUM_RANK)
        val REQUISITES_NOT_MEET = loadWithPrefix(ConfigMessageKeys.REQUISITES_NOT_MEET)

        private fun load(messageKey: String) : String {
            return decorate(config.getString(messageKey, "")!!)
        }

        private fun loadWithPrefix(messageKey: String) : String {
            return PREFIX + load(messageKey)
        }

        private fun decorate(message: String) : String {
            return ColorUtils.translate(message)
        }
    }


}