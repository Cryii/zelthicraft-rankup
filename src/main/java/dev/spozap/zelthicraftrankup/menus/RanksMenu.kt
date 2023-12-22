package dev.spozap.zelthicraftrankup.menus

import dev.spozap.zelthicraftrankup.ZCRankup
import dev.spozap.zelthicraftrankup.models.Rank
import dev.spozap.zelthicraftrankup.utils.ColorUtils
import dev.triumphteam.gui.builder.item.ItemBuilder
import dev.triumphteam.gui.guis.Gui
import dev.triumphteam.gui.guis.GuiItem
import dev.triumphteam.gui.guis.PaginatedGui
import net.kyori.adventure.text.Component
import org.bukkit.Material
import org.bukkit.NamespacedKey
import org.bukkit.entity.Player
import org.bukkit.persistence.PersistentDataType

private const val INV_ROWS = 3
private const val INV_PAGE_SIZE = 27

class RanksMenu(ranks: List<Rank>) {

    private var ranksMenu: PaginatedGui = Gui.paginated().disableAllInteractions().title(Component.text("Rangos")).rows(INV_ROWS).pageSize(INV_PAGE_SIZE).create()

    init {
        ranks.forEachIndexed { index, rank ->
            val rankItem = generateRankItem(rank)
            placeRank(index, rankItem)
        }
    }

    fun open(player: Player) {
        ranksMenu.open(player)
    }

    private fun placeRank(index: Int, item: GuiItem) {
        ranksMenu.setItem(index, item)
    }

    private fun generateRankItem(rank: Rank): GuiItem {

        return ItemBuilder.from(Material.OBSIDIAN)
                .name(Component.text(ColorUtils.translate(rank.displayName)))
                .lore(rank.lore.map { loreLine -> Component.text(ColorUtils.translate(loreLine)) })
                .pdc {
                    it.set(NamespacedKey(ZCRankup.plugin, "zr_rankid"), PersistentDataType.STRING, rank.id)
                }
                .asGuiItem()
    }

}