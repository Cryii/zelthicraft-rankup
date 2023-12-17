package dev.spozap.zelthicraftrankup.utils

import dev.spozap.zelthicraftrankup.ZCRankup

class RankValidator {

    private val permissionsApi = ZCRankup.permissionsApi

    fun isValid(groupName: String) : Boolean {
        return permissionsApi.groups.contains(groupName)
    }


}