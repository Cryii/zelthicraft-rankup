package dev.spozap.zelthicraftrankup.utils

import dev.spozap.zelthicraftrankup.Main

class RankValidator {

    private val permissionsApi = Main.permissionsApi

    fun isValid(groupName: String) : Boolean {
        return permissionsApi.groups.contains(groupName)
    }

}