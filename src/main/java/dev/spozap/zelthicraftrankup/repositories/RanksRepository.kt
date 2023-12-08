package dev.spozap.zelthicraftrankup.repositories

import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.utils.ConfigurationFile
import dev.spozap.zelthicraftrankup.utils.RankValidator

class RanksRepository {

    private val ranksFile: ConfigurationFile = ConfigurationFile("ranks")
    private val validator: RankValidator = RankValidator()

    init {
        ranksFile.setup()

        if (ranksFile.fileConfiguration.getConfigurationSection("ranks") == null) {
            ranksFile.fileConfiguration.createSection("ranks")
        }
    }

    fun load(): LinkedHashMap<String, Rank> {

        val ranks = linkedMapOf<String, Rank>()
        val config = ranksFile.fileConfiguration

        val configRanks = config.getConfigurationSection("ranks")?.getKeys(false)

        if (configRanks == null) return ranks

        for (rankId in configRanks) {

            if (validator.isValid(rankId)) {
                println("El rango $rankId es valido")
                val rank = Rank(rankId, rankId, listOf())
                ranks[rankId] = rank
            }

        }

        return ranks
    }

    fun save() {
        ranksFile.save()
    }

}