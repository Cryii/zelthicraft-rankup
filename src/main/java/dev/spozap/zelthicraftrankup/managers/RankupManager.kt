package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.repositories.RanksRepository
import org.bukkit.entity.Player

class RankupManager {

    private val repository : RanksRepository = RanksRepository()
    private var ranks: LinkedHashMap<String, Rank> = repository.load()

    fun showRanks(player: Player) {
        ranks.forEach { rankId, rank -> player.sendMessage("Rango: $rankId") }
    }

    fun saveRanks() {
        repository.save()
    }





}