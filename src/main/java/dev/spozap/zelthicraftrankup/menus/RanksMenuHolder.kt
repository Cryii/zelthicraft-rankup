package dev.spozap.zelthicraftrankup.menus

import dev.spozap.zelthicraftrankup.models.Rank
import org.bukkit.Bukkit
import org.bukkit.Material
import org.bukkit.event.inventory.InventoryType
import org.bukkit.inventory.Inventory
import org.bukkit.inventory.InventoryHolder
import org.bukkit.inventory.ItemStack

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
        inventory.setItem(index, item)
    }

    private fun generateRankItem(rank: Rank): ItemStack {

        val rankItem = ItemStack(Material.OBSIDIAN)
        val itemMeta = rankItem.itemMeta!!

        itemMeta.setDisplayName(rank.displayName)

        rankItem.setItemMeta(itemMeta)

        return rankItem

    }

}