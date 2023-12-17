package dev.spozap.zelthicraftrankup.listeners

import dev.spozap.zelthicraftrankup.Main
import dev.spozap.zelthicraftrankup.menus.RanksMenuHolder
import dev.spozap.zelthicraftrankup.models.Rankup
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.inventory.InventoryClickEvent
import org.bukkit.persistence.PersistentDataType

class PlayerListeners : Listener {

    private val ranksManager = Main.ranksManager
    private val rankupManager = Main.rankupManager

    @EventHandler
    fun onMenuClick(event: InventoryClickEvent) {

        if (event.inventory.holder !is RanksMenuHolder) return

        event.isCancelled = true

        val player = event.whoClicked as Player
        val ranksMenuInventoryHolder : RanksMenuHolder = event.inventory.holder as RanksMenuHolder

        when (event.currentItem?.type) {
            Material.OBSIDIAN -> {
                println("entra obsidian")
                val rankPdc = event.currentItem!!.itemMeta!!.persistentDataContainer
                val rankId = rankPdc.get(NamespacedKey(Main.plugin, "zr_rankid"), PersistentDataType.STRING) ?: ""
                println("rankpdc es: $rankId")
                val rank = ranksManager.getRankById(rankId)
                println("sig rango es: ${rank?.id}")
                if (rank != null) {
                    println("entra rank != null")
                    val nextRank = ranksManager.getNextRank(rankId)
                    println("el sig rango es : ${nextRank!!.id}")
                    val rankup = Rankup(rank, nextRank, player)

                    val possible = rankupManager.isRankupPossible(rankup)

                    if (possible) {
                        player.sendMessage("Es posible subir")
                    }

                }
            }
            else -> {

            }
        }

    }

}