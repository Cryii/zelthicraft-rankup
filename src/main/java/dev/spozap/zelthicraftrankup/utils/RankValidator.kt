package dev.spozap.zelthicraftrankup.utils

import dev.spozap.zelthicraftrankup.Main

class RankValidator {

    private val luckPermsApi = Main.luckPermsAPI

    fun isLuckPermsGroup(groupName: String) : Boolean {
        return luckPermsApi.groupManager.getGroup(groupName) != null
    }

}