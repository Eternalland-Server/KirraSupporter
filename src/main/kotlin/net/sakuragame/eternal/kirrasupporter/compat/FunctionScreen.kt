package net.sakuragame.eternal.kirrasupporter.compat

import com.taylorswiftcn.megumi.uifactory.event.comp.UIFCompSubmitEvent
import ink.ptms.zaphkiel.ZaphkielAPI
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.gemseconomy.api.GemsEconomyAPI
import net.sakuragame.eternal.gemseconomy.currency.EternalCurrency
import net.sakuragame.eternal.justmessage.api.MessageAPI
import net.sakuragame.eternal.kirrasupporter.KirraSupporter
import net.sakuragame.eternal.kirrasupporter.Reward
import net.sakuragame.eternal.kirrasupporter.compat.screen.MVPScreen
import net.sakuragame.eternal.kirrasupporter.compat.screen.SVPScreen
import net.sakuragame.eternal.kirrasupporter.compat.screen.VIPScreen
import org.bukkit.Bukkit
import org.bukkit.entity.Player
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit
import taboolib.platform.util.hasItem
import taboolib.platform.util.takeItem

@Suppress("SpellCheckingInspection", "unused")
object FunctionScreen {

    private val cardItem by lazy {
        ZaphkielAPI.getItem("vip_card")!!.rebuildToItemStack()
    }

    fun generateList(groupName: String): MutableList<Reward> {
        val toReturn = mutableListOf<Reward>()
        KirraSupporter.conf.getStringList("rewards.$groupName.currency").forEach {
            val splitStr = it.split("@")
            toReturn.add(Reward(Reward.Companion.RewardType.CURRENCY, splitStr[0], splitStr[1].toInt()))
        }
        KirraSupporter.conf.getStringList("rewards.$groupName.items").forEach {
            val splitStr = it.split("@")
            toReturn.add(Reward(Reward.Companion.RewardType.ITEM, splitStr[0], splitStr[1].toInt()))
        }
        return toReturn
    }

    fun updateVars(player: Player) {
        submit(delay = 2L, async = true) {
            PacketSender.sendSyncPlaceholder(player, mutableMapOf<String, String>().also {
                it["ksupporter_vip"] = "false"
                it["ksupporter_svp"] = "false"
                it["ksupporter_mvp"] = "false"
                if (player.hasPermission("mvp")) {
                    it["ksupporter_vip"] = "true"
                    it["ksupporter_svp"] = "true"
                    it["ksupporter_mvp"] = "true"
                    return@also
                }
                if (player.hasPermission("svp")) {
                    it["ksupporter_svp"] = "true"
                    it["ksupporter_vip"] = "true"
                    return@also
                }
                if (player.hasPermission("vip")) {
                    it["ksupporter_vip"] = "true"
                    return@also
                }
            })
        }
    }

    @Suppress("KotlinConstantConditions", "DUPLICATE_LABEL_IN_WHEN")
    @SubscribeEvent
    fun e(e: UIFCompSubmitEvent) {
        val player = e.player
        val key = e.compID
        if (key == "vip_buy" || key == "svp_buy" || key == "mvp_buy") {
            player.closeInventory()
        } else {
            return
        }
        when (key) {
            "vip_buy" -> {
                if (player.hasPermission("vip")) return
                val hasItem = player.inventory.hasItem(1) { it.isSimilar(cardItem) }
                if (!hasItem) {
                    player.closeInventory()
                    MessageAPI.sendActionTip(player, "message-player-failed-buy")
                    return
                }
                player.inventory.takeItem(1) { it.isSimilar(cardItem) }
                setGroup(player, "vip")
                VIPScreen.rewards.forEach {
                    it.give(player)
                }
            }
            "svp_buy" -> {
                if (player.hasPermission("svp")) return
                if (!executeDebitFromPlayer(player, 100000.0)) return
                setGroup(player, "svp")
                SVPScreen.rewards.forEach {
                    it.give(player)
                }
            }
            "mvp_buy" -> {
                if (player.hasPermission("mvp")) return
                if (!executeDebitFromPlayer(player, if (player.hasPermission("svp")) 300000.0 else 400000.0)) {
                    return
                }
                MVPScreen.rewards.forEach {
                    it.give(player)
                }
            }
            else -> return
        }
    }

    fun sendSlotItems(player: Player) {
        val vipItems = VIPScreen.rewards
            .filter { it.type == Reward.Companion.RewardType.ITEM }
            .map {
                ZaphkielAPI.getItem(it.value, player)?.rebuildToItemStack(player).also { item ->
                    item?.amount = it.amount
                }
            }
        val svpItems = SVPScreen.rewards
            .filter { it.type == Reward.Companion.RewardType.ITEM }
            .map {
                ZaphkielAPI.getItem(it.value, player)?.rebuildToItemStack(player).also { item ->
                    item?.amount = it.amount
                }
            }
        val mvpItems = MVPScreen.rewards.filter { it.type == Reward.Companion.RewardType.ITEM }
            .map {
                ZaphkielAPI.getItem(it.value, player)?.rebuildToItemStack(player).also { item ->
                    item?.amount = it.amount
                }
            }
        vipItems.forEachIndexed { index, item ->
            val slotIndex = index + 1
            PacketSender.putClientSlotItem(player, "vip_item_$slotIndex", item)
        }
        svpItems.forEachIndexed { index, item ->
            val slotIndex = index + 1
            PacketSender.putClientSlotItem(player, "svp_item_$slotIndex", item)
        }
        mvpItems.forEachIndexed { index, item ->
            val slotIndex = index + 1
            PacketSender.putClientSlotItem(player, "mvp_item_$slotIndex", item)
        }
    }

    private fun executeDebitFromPlayer(player: Player, value: Double): Boolean {
        if (GemsEconomyAPI.getBalance(player.uniqueId, EternalCurrency.Points) >= value) {
            GemsEconomyAPI.withdraw(player.uniqueId, value, EternalCurrency.Points, "购买会员")
            return true
        }
        MessageAPI.sendActionTip(player, "message-player-failed-buy")
        return false
    }

    private fun setGroup(player: Player, groupName: String) {
        val userData = KirraSupporter.luckPermsAPI.userManager.getUser(player.uniqueId) ?: return
        userData.primaryGroup = groupName
    }
}