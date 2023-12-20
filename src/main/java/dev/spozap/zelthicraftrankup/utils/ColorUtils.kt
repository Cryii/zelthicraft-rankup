package dev.spozap.zelthicraftrankup.utils

import org.bukkit.ChatColor

class ColorUtils {

    companion object {

        fun translate(message: String) : String {
            return ChatColor.translateAlternateColorCodes('&', message)
        }

        fun translate(message: List<String>) : List<String> {
            return message.map { line -> translate(line) }
        }


    }

}