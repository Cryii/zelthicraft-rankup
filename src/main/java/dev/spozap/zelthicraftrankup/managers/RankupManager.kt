package dev.spozap.zelthicraftrankup.managers

import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.repositories.RanksRepository

class RankupManager {

    private val repository : RanksRepository = RanksRepository()
    private var ranks: LinkedHashMap<String, Rank> = repository.load()


    fun saveRanks() {
        repository.save()
    }





}