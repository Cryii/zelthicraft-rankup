package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.constants.ConfigMessageKeys
import dev.spozap.zelthicraftrankup.utils.ColorUtils
import org.bukkit.configuration.file.FileConfiguration

class ConfigManager {

    companion object {

        private var config : FileConfiguration = ZCRankup.plugin.config


        var PREFIX = load(ConfigMessageKeys.PREFIX)
        var NO_PERMISSIONS = loadWithPrefix(ConfigMessageKeys.NO_PERMISSIONS)
        var NO_RANKUP_AVAILABLE = loadWithPrefix(ConfigMessageKeys.NO_RANKUP_AVAILABLE)
        var MAXIMUM_RANK = loadWithPrefix(ConfigMessageKeys.MAXIMUM_RANK)
        var REQUISITES_NOT_MEET = loadWithPrefix(ConfigMessageKeys.REQUISITES_NOT_MEET)

        private fun load(messageKey: String) : String {
            return decorate(config.getString(messageKey, "")!!)
        }

        private fun loadWithPrefix(messageKey: String) : String {
            return PREFIX + load(messageKey)
        }

        private fun decorate(message: String) : String {
            return ColorUtils.translate(message)
        }

        fun reloadConfig() {
            ZCRankup.plugin.reloadConfig()
            config = ZCRankup.plugin.config

            PREFIX = load(ConfigMessageKeys.PREFIX)
            NO_PERMISSIONS = loadWithPrefix(ConfigMessageKeys.NO_PERMISSIONS)
            NO_RANKUP_AVAILABLE = loadWithPrefix(ConfigMessageKeys.NO_RANKUP_AVAILABLE)
            MAXIMUM_RANK = loadWithPrefix(ConfigMessageKeys.MAXIMUM_RANK)
            REQUISITES_NOT_MEET = loadWithPrefix(ConfigMessageKeys.REQUISITES_NOT_MEET)
        }
    }


}