package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.menus.RanksMenuHolder
import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.repositories.RanksRepository
import org.bukkit.entity.Player

class RankupManager {

    private val repository : RanksRepository = RanksRepository()
    private var ranks: LinkedHashMap<String, Rank> = repository.load()

    fun showRanks(player: Player) {
        val invHolder = RanksMenuHolder(ranks.values.toList())
        player.openInventory(invHolder.inventory)
    }

    fun saveRanks() {
        repository.save()
    }

}