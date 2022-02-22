package net.sakuragame.eternal.kirrasupporter

import ink.ptms.zaphkiel.ZaphkielAPI
import net.sakuragame.eternal.gemseconomy.api.GemsEconomyAPI
import net.sakuragame.eternal.gemseconomy.currency.EternalCurrency
import net.sakuragame.eternal.kirrasupporter.Reward.Companion.RewardType.CURRENCY
import net.sakuragame.eternal.kirrasupporter.Reward.Companion.RewardType.ITEM
import org.bukkit.entity.Player

data class Reward(val type: RewardType, val value: String, val amount: Int) {

    fun give(player: Player) {
        when (type) {
            CURRENCY -> {
                val currency = EternalCurrency.values().find { it.name.lowercase() == value.lowercase() } ?: return
                GemsEconomyAPI.deposit(player.uniqueId, amount.toDouble(), currency)
            }
            ITEM -> {
                val item = ZaphkielAPI.getItem(value) ?: return
                repeat(amount) {
                    player.inventory.addItem(item.rebuildToItemStack())
                }
            }
        }
    }

    companion object {

        enum class RewardType {
            CURRENCY, ITEM;
        }
    }
}
