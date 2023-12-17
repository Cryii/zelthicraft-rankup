package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.Main
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

    fun hasRankupRank(player: Player) : Boolean {
        return Main.permissionsApi.getPlayerGroups(player).any { rank -> ranks.contains(rank) }
    }

    fun getFirstRankupPlayerRank(player: Player): Rank {
        val rankName = Main.permissionsApi.getPlayerGroups(player)
                .first { ranks.containsKey(it) }
        return ranks[rankName]!!
    }

    fun hasMaximumRank(player: Player) : Boolean {
        val lastRankId = ranks.values.lastOrNull()?.id ?: ""
        return Main.permissionsApi.getPlayerGroups(player).contains(lastRankId)
    }


    fun saveRanks() {
        repository.save()
    }

}