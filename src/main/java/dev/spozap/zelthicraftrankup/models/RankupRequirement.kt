package dev.spozap.zelthicraftrankup.models

import org.bukkit.entity.Player

abstract class RankupRequirement {

    abstract fun isMet(player: Player) : Boolean

    abstract fun execute(player: Player)

}