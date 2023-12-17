package dev.spozap.zelthicraftrankup.utils

import dev.spozap.zelthicraftrankup.ZCRankup
import org.bukkit.configuration.file.FileConfiguration
import org.bukkit.configuration.file.YamlConfiguration
import java.io.File

class ConfigurationFile(private val fileName: String) {

    private lateinit var file : File
    lateinit var fileConfiguration: FileConfiguration

    fun setup() {
        file = File(ZCRankup.plugin.dataFolder, "$fileName.yml")

        if (!file.exists()) {
            try {
                file.createNewFile()
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }

        fileConfiguration = YamlConfiguration.loadConfiguration(file)

    }

    fun save() {
        try {
            fileConfiguration.save(file)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }


}