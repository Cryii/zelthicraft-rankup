package dev.spozap.zelthicraftrankup.repositories

import dev.spozap.zelthicraftrankup.models.MoneyRankupRequirement
import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.models.RankupRequirement
import dev.spozap.zelthicraftrankup.models.TimeRankupRequirement
import dev.spozap.zelthicraftrankup.utils.ConfigurationFile
import dev.spozap.zelthicraftrankup.utils.RankValidator
import org.bukkit.configuration.ConfigurationSection

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

            if (!validator.isValid(rankId)) continue

            val requirements = mutableListOf<RankupRequirement>()

            val rankSection : ConfigurationSection = config.getConfigurationSection("ranks.$rankId")!!

            val rankDisplayName = rankSection.getString("display-name") ?: ""

            val rankSkullTexture = rankSection.getString("skull-texture") ?: ""

            val rankLore : List<String> = rankSection.getStringList("lore")

            loadCondition("money", rankSection, requirements) { MoneyRankupRequirement(it.toDouble()) }
            loadCondition("time", rankSection, requirements) { TimeRankupRequirement(it.toLong()) }

            ranks[rankId] = Rank(rankId, requirements, rankDisplayName, rankLore, rankSkullTexture)

        }

        return ranks
    }

    private fun loadCondition(conditionName: String, section: ConfigurationSection, requirements: MutableList<RankupRequirement>, conditionProvider: (String) -> RankupRequirement) {
        val conditionValue = section.getString(conditionName)
        if (conditionValue != null) {
            requirements.add(conditionProvider.invoke(conditionValue))
        }
    }

    fun save() {
        ranksFile.save()
    }

}