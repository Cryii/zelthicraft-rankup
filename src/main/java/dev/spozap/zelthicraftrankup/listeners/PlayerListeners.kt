package dev.spozap.zelthicraftrankup.listeners

import dev.spozap.zelthicraftrankup.ZCRankup
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

    private val ranksManager = ZCRankup.ranksManager
    private val rankupManager = ZCRankup.rankupManager

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
                val rankId = rankPdc.get(NamespacedKey(ZCRankup.plugin, "zr_rankid"), PersistentDataType.STRING) ?: ""
                val rank = ranksManager.getRankById(rankId)
                if (rank != null) {
                    val nextRank = ranksManager.getNextRank(rankId)
                    val rankup = Rankup(rank, nextRank, player)

                    val possible = rankupManager.isRankupPossible(rankup)

                    if (possible) {
                        player.sendMessage("Es posible subir")
                        rankupManager.rankup(rankup)
                    }

                }
            }
            else -> {

            }
        }

    }

}