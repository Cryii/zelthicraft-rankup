package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.menus.RanksMenuHolder
import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.repositories.RanksRepository
import org.bukkit.entity.Player

class RanksManager {

    private val repository : RanksRepository = RanksRepository()
    private var ranks: LinkedHashMap<String, Rank> = repository.load()

    fun showRanks(player: Player) {
        val invHolder = RanksMenuHolder(ranks.values.toList())
        player.openInventory(invHolder.inventory)
    }

    fun getRankById(id: String) : Rank? {
        return ranks.get(id)
    }

    fun getNextRank(currentRankId: String) : Rank? {
        val keyList = ranks.keys.toList()
        val currentRankIndex = keyList.indexOf(currentRankId)

        if (currentRankIndex != -1 && currentRankIndex < keyList.size - 1) {
            val nextRankIndex = keyList[currentRankIndex + 1]
            return ranks[nextRankIndex]
        }

        return null
    }

    fun saveRanks() {
        repository.save()
    }

}