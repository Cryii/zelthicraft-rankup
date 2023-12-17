package dev.spozap.zelthicraftrankup.menus

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.models.Rank
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack
import org.bukkit.persistence.PersistentDataType

class RanksMenuHolder(ranks: List<Rank>) : InventoryHolder {

    private var ranksMenu: Inventory = Bukkit.createInventory(this, InventoryType.CHEST, "Rangos disponibles")

    init {
        ranks.forEachIndexed { index, rank ->
            val rankItem = generateRankItem(rank)
            placeRank(index, rankItem)
        }
    }

    override fun getInventory(): Inventory {
        return this.ranksMenu
    }

    private fun placeRank(index: Int, item: ItemStack) {
        ranksMenu.setItem(index, item)
    }

    private fun generateRankItem(rank: Rank): ItemStack {

        val rankItem = ItemStack(Material.OBSIDIAN)
        val itemMeta = rankItem.itemMeta!!
        val itemPdc = itemMeta.persistentDataContainer

        itemPdc.set(NamespacedKey(ZCRankup.plugin, "zr_rankid"), PersistentDataType.STRING, rank.id)
        itemMeta.setDisplayName(rank.id)
        rankItem.setItemMeta(itemMeta)

        return rankItem

    }

}