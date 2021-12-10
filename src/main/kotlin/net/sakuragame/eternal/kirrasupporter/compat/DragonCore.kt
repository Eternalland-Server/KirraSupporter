package net.sakuragame.eternal.kirrasupporter.compat

import com.taylorswiftcn.megumi.uifactory.generate.function.Statements
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.type.FunctionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import ink.ptms.zaphkiel.ZaphkielAPI
import net.sakuragame.eternal.dragoncore.api.KeyPressEvent
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.justmessage.api.MessageAPI
import net.sakuragame.eternal.kirrasupporter.KirraSupporter
import net.sakuragame.kirracore.bukkit.KirraCoreBukkitAPI
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.event.SubscribeEvent
import taboolib.common.platform.function.submit
import taboolib.platform.compat.replacePlaceholder
import taboolib.platform.util.giveItem
import taboolib.platform.util.hasItem
import taboolib.platform.util.takeItem

@Suppress("SpellCheckingInspection")
object DragonCore {

    val cardItem by lazy {
        ZaphkielAPI.getItem("vip_card")!!.rebuildToItemStack()
    }

    lateinit var shopUI: ScreenUI

    @Awake(LifeCycle.ENABLE)
    fun init() {
        shopUI = ScreenUI("vip")
            .addFunctions(FunctionType.Open, "global.hud_visible = true;")
            .addFunctions(FunctionType.Close, "global.hud_visible = false;")
            .addComponent(TextureComp("v1_body", "hud/vip/bg.png")
                .setXY("60", "180")
                .setWidth("270")
                .setHeight("220")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v1_1_body", "hud/vip_other_players/attribute_bg.png")
                .setXY("v1_body.x+16", "v1_bt.y+30+5")
                .setWidth("162")
                .setHeight("64")
                .setAlpha(0.7)
            )
            .addComponent(LabelComp("v1_1_body_label", "")
                .setTexts(Statements()
                    .add("&a部分特权介绍: ")
                    .add("&7· &f进入副本次数提升.")
                    .add("&7· &f商场获得 10% 折扣.")
                    .add("&7· &f¹ 专属称号.")
                    .add("&7· &f每日会员礼包.")
                    .build())
                .setXY("v1_1_body.x+5", "v1_1_body.y+5")
            )
            .addComponent(TextureComp("v1", "hud/vip/v1.png")
                .setXY("v1_body.x+193", "v1_body.y+17")
                .setWidth("64")
                .setHeight("64")
            )
            .addComponent(TextureComp("v1_bt", "hud/team/card.png")
                .setXY("v1_body.x+16", "v1_body.y+30")
                .setWidth("162")
                .setHeight("30")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v1_np1", "hud/vip/np1.png")
                .setXY("v1_body.x", "v1_body.y-v1_np1.height+25")
                .setWidth("47")
                .setHeight("80")
            )
            .addComponent(TextureComp("v1_np1_dh", "ui/common/quest_select_bg.png")
                .setXY("v1_np1.x+v1_np1.width+5", "v1_np1.y+20")
                .setWidth("11")
                .setHeight("26")
            )
            .addComponent(TextureComp("v1_np1_dh_bg", "ui/common/black_gradient_vip.png")
                .setXY("v1_np1_dh.x+11", "v1_np1_dh.y-17")
                .setWidth("162")
                .setHeight("60")
            )
            .addComponent(LabelComp("v1_np1_label", "")
                .setTexts(Statements()
                    .add("&a会员助手: ")
                    .add("&f所有的会员都是永久的, 早买早享受!")
                    .add("&f记得领取每日礼包哦.")
                    .add("")
                    .add("&7&o合理管理游戏时间, 请理性消费.")
                    .build())
                .setXY("v1_np1_dh_bg.x+2", "v1_np1_dh_bg.y+2")
            )
            .addComponent(LabelComp("v1_bt_label", "")
                .setTexts("&f&l白银会员&c (永久)")
                .setXY("v1_bt.x+(v1_bt.width-v1_bt_label.width)/2", "v1_bt.y+9")
            )
            .addComponent(TextureComp("v1_mr_bg", "ui/common/black_gradient_vip.png")
                .setXY("v1_mr_label.x", "v1_mr_label.y-2")
                .setWidth("162")
                .setHeight("22")
            )
            .addComponent(LabelComp("v1_mr_label", "")
                .setTexts(Statements()
                    .add("&f&l每日领取: ")
                    .add("&eΓx5,000,000, &e☪x50,000, &cβx1")
                    .build())
                .setXY("v1_1_body.x", "v1_1_body.y+64+5")
            )
            .addComponent(TextureComp("v1_lj_bg", "ui/common/golden_gradient_vip.png")
                .setXY("v1_lj_label.x", "v1_lj_label.y-2")
                .setWidth("162")
                .setHeight("45")
            )
            .addComponent(LabelComp("v1_lj_label", "")
                .setTexts(Statements()
                    .add("&f&l立即获得: ")
                    .add("&eΓx50,000,000, &e☪x500,000, &cβx10")
                    .build())
                .setXY("v1_mr_label.x", "v1_mr_label.y+26")
            )
            .addComponent(TextureComp("v1_wp_1", "ui/pack/slot_bg.png")
                .setXY("v1_bt.x+10", "v1_lj_label.y+24")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v1_wp_2", "ui/pack/slot_bg.png")
                .setXY("v1_wp_1.x+16+4", "v1_wp_1.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v1_wp_3", "ui/pack/slot_bg.png")
                .setXY("v1_wp_2.x+16+4", "v1_wp_2.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v1_wp_4", "ui/pack/slot_bg.png")
                .setXY("v1_wp_3.x+16+4", "v1_wp_3.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v1_gm_bg", "0,0,0,100")
                .setXY("v1_gm.x", "v1_gm.y-16")
                .setWidth("64")
                .setHeight("24")
                .setVisible("func.PlaceholderAPI_Get('ksupporter_vip') == false")
            )
            .addComponent(LabelComp("v1_gm_bg_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_vip') == true ? '' : '&e抵用券 x 1'")
                .setXY("v1_gm_bg.x+(v1_gm_bg.width-v1_gm_bg_label.width)/2+2", "v1_gm_bg.y+5")
            )
            .addComponent(TextureComp("v1_gm", "ui/common/button_normal_a.png")
                .setXY("v1.x", "v1_lj_bg.y+45-24")
                .setWidth("64")
                .setHeight("24")
                .addAction(ActionType.Left_Click)
                .addAction(ActionType.Left_Click, Statements()
                    .add("v1_gm.texture = 'ui/common/button_normal_a_press.png';")
                    .build())
                .addAction(ActionType.Left_Release, Statements()
                    .add("v1_gm.texture = 'ui/common/button_normal_a.png';")
                    .build())
            )
            .addComponent(LabelComp("v1_gm_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_vip') == true ? '&f&l已购买' : '&f&l购买'")
                .setXY("v1_gm.x+(v1_gm.width-v1_gm_label.width)/2+2", "v1_gm.y+6")
            )
            .addComponent(TextureComp("v2_body", "hud/vip/bg.png")
                .setXY("v1_body.x+v1_body.width+10", "v1_body.y")
                .setWidth("270")
                .setHeight("220")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v2_1_body", "hud/vip_other_players/attribute_bg.png")
                .setXY("v2_body.x+16", "v2_bt.y+30+5")
                .setWidth("162")
                .setHeight("64")
                .setAlpha(0.7)
            )
            .addComponent(LabelComp("v2_1_body_label", "")
                .setTexts(Statements()
                    .add("&a部分特权介绍: ")
                    .add("&7· &f部分副本无限进入, 大部分副本次数提升.")
                    .add("&7· &f商场获得 20% 折扣.")
                    .add("&7· &f² 专属称号.")
                    .add("&7· &f每日会员礼包. &6&l升级款!")
                    .build())
                .setXY("v2_1_body.x+5", "v2_1_body.y+5")
            )
            .addComponent(TextureComp("v2", "hud/vip/v2.png")
                .setXY("v2_body.x+193", "v2_body.y+17")
                .setWidth("64")
                .setHeight("64")
            )
            .addComponent(TextureComp("v2_bt", "hud/team/card.png")
                .setXY("v2_body.x+16", "v2_body.y+30")
                .setWidth("162")
                .setHeight("30")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v2_np1", "hud/vip/np2.png")
                .setXY("v2_body.x-20", "v2_body.y-v2_np1.height+25")
                .setWidth("82")
                .setHeight("95")
            )
            .addComponent(TextureComp("v2_np1_dh", "ui/common/quest_select_bg.png")
                .setXY("v2_np1.x+v2_np1.width-10", "v1_np1_dh.y")
                .setWidth("11")
                .setHeight("26")
            )
            .addComponent(TextureComp("v2_np1_dh_bg", "ui/common/black_gradient_vip.png")
                .setXY("v2_np1_dh.x+11", "v2_np1_dh.y-17")
                .setWidth("162")
                .setHeight("60")
            )
            .addComponent(LabelComp("v2_np1_label", "")
                .setTexts(Statements()
                    .add("&a会员助手: ")
                    .add("&f铂金会员可以领取购买氪金道具的神石.")
                    .add("&f记得领取每日礼包哦.")
                    .add("")
                    .add("&7&o合理管理游戏时间, 请理性消费.")
                    .build())
                .setXY("v2_np1_dh_bg.x+2", "v2_np1_dh_bg.y+2")
            )
            .addComponent(LabelComp("v2_bt_label", "")
                .setTexts("&6&l铂金会员&c (永久)")
                .setXY("v2_bt.x+(v2_bt.width-v2_bt_label.width)/2", "v2_bt.y+9")
            )
            .addComponent(TextureComp("v2_mr_bg", "ui/common/black_gradient_vip.png")
                .setXY("v2_mr_label.x", "v2_mr_label.y-2")
                .setWidth("162")
                .setHeight("22")
            )
            .addComponent(LabelComp("v2_mr_label", "")
                .setTexts(Statements()
                    .add("&f&l每日领取: ")
                    .add("&eΓx5,000,000, &e☪x100,000, &e㊉x1,000, &cβx3, SVP 喇叭x3")
                    .build())
                .setXY("v2_1_body.x", "v2_1_body.y+64+5")
            )
            .addComponent(TextureComp("v2_lj_bg", "ui/common/golden_gradient_vip.png")
                .setXY("v2_lj_label.x", "v2_lj_label.y-2")
                .setWidth("162")
                .setHeight("45")
            )
            .addComponent(LabelComp("v2_lj_label", "")
                .setTexts(Statements()
                    .add("&f&l立即获得: ")
                    .add("&eΓx 100,000,000, &eర☪x1,000,000, &cβx30")
                    .build())
                .setXY("v2_mr_label.x", "v2_mr_label.y+26")
            )
            .addComponent(TextureComp("v2_wp_1", "ui/pack/slot_bg.png")
                .setXY("v2_bt.x+10", "v2_lj_label.y+24")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v2_wp_2", "ui/pack/slot_bg.png")
                .setXY("v2_wp_1.x+16+4", "v2_wp_1.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v2_wp_3", "ui/pack/slot_bg.png")
                .setXY("v2_wp_2.x+16+4", "v2_wp_2.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v2_wp_4", "ui/pack/slot_bg.png")
                .setXY("v2_wp_3.x+16+4", "v2_wp_3.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v2_gm_bg", "0,0,0,100")
                .setXY("v2_gm.x", "v2_gm.y-16")
                .setWidth("64")
                .setHeight("24")
                .setVisible("func.PlaceholderAPI_Get('ksupporter_mvp') == false")
            )
            .addComponent(LabelComp("v2_gm_bg_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_svp') == true ? '' : '&eరె 100,000 神石'")
                .setXY("v2_gm_bg.x+(v2_gm_bg.width-v2_gm_bg_label.width)/2", "v2_gm_bg.y+5")
            )
            .addComponent(TextureComp("v2_gm", "ui/common/button_normal_a.png")
                .setXY("v2.x", "v2_lj_bg.y+45-24")
                .setWidth("64")
                .setHeight("24")
                .addAction(ActionType.Left_Click)
                .addAction(ActionType.Left_Click, Statements()
                    .add("v2_gm.texture = 'ui/common/button_normal_a_press.png';")
                    .build())
                .addAction(ActionType.Left_Release, Statements()
                    .add("v2_gm.texture = 'ui/common/button_normal_a.png';")
                    .build())
            )
            .addComponent(LabelComp("v2_gm_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_svp') == true ? '&f&l已购买' : '&f&l购买'")
                .setXY("v2_gm.x+(v2_gm.width-v2_gm_label.width)/2+2", "v2_gm.y+6")
            )
            .addComponent(TextureComp("v3_body", "hud/vip/bg.png")
                .setXY("v2_body.x+v2_body.width+10", "v2_body.y")
                .setWidth("270")
                .setHeight("220")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v3_1_body", "hud/vip_other_players/attribute_bg.png")
                .setXY("v3_body.x+16", "v3_bt.y+30+5")
                .setWidth("162")
                .setHeight("64")
                .setAlpha(0.7)
            )
            .addComponent(LabelComp("v3_1_body_label", "")
                .setTexts(Statements()
                    .add("&a部分特权介绍: ")
                    .add("&7· &f大部分副本无限进入, 更多副本次数提升")
                    .add("&7· &f商场获得 30% 折扣.")
                    .add("&7· &f³ 专属称号.")
                    .add("&7· &f每日会员礼包. &b&l超级款!")
                    .build())
                .setXY("v3_1_body.x+5", "v3_1_body.y+5")
            )
            .addComponent(TextureComp("v3", "hud/vip/v3.png")
                .setXY("v3_body.x+193", "v3_body.y+17")
                .setWidth("64")
                .setHeight("64")
            )
            .addComponent(TextureComp("v3_bt", "hud/team/card.png")
                .setXY("v3_body.x+16", "v3_body.y+30")
                .setWidth("162")
                .setHeight("30")
                .setAlpha(0.7)
            )
            .addComponent(TextureComp("v3_np1", "hud/vip/np3.png")
                .setXY("v3_body.x-10", "v3_body.y-v3_np1.height+25")
                .setWidth("65")
                .setHeight("82")
            )
            .addComponent(TextureComp("v3_np1_dh", "ui/common/quest_select_bg.png")
                .setXY("v3_np1.x+v3_np1.width-10", "v2_np1_dh.y")
                .setWidth("11")
                .setHeight("26")
            )
            .addComponent(TextureComp("v3_np1_dh_bg", "ui/common/black_gradient_vip.png")
                .setXY("v3_np1_dh.x+11", "v3_np1_dh.y-17")
                .setWidth("162")
                .setHeight("60")
            )
            .addComponent(LabelComp("v3_np1_label", "")
                .setTexts(Statements()
                    .add("&a会员助手: ")
                    .add("&f钻石会员可以领取购买氪金道具的神石.")
                    .add("&f记得领取每日礼包哦.")
                    .add("")
                    .add("&7&o合理管理游戏时间, 请理性消费.")
                    .build())
                .setXY("v3_np1_dh_bg.x+2", "v3_np1_dh_bg.y+2")
            )
            .addComponent(LabelComp("v3_bt_label", "")
                .setTexts("&b&l钻石会员&c (永久)")
                .setXY("v3_bt.x+(v3_bt.width-v3_bt_label.width)/2", "v3_bt.y+9")
            )
            .addComponent(TextureComp("v3_mr_bg", "ui/common/black_gradient_vip.png")
                .setXY("v3_mr_label.x", "v3_mr_label.y-2")
                .setWidth("162")
                .setHeight("22")
            )
            .addComponent(LabelComp("v3_mr_label", "")
                .setTexts(Statements()
                    .add("&f&l每日领取: ")
                    .add("&eΓx30,000,000, &e☪x300,000, &e㊉x3,000  &cβx10")
                    .build())
                .setXY("v3_1_body.x", "v3_1_body.y+64+5")
            )
            .addComponent(TextureComp("v3_lj_bg", "ui/common/golden_gradient_vip.png")
                .setXY("v3_lj_label.x", "v3_lj_label.y-2")
                .setWidth("162")
                .setHeight("45")
            )
            .addComponent(LabelComp("v3_lj_label", "")
                .setTexts(Statements()
                    .add("&f&l立即获得: ")
                    .add("&eΓx500,000,000, &e☪x3,000,000 , &cβx10")
                    .build())
                .setXY("v3_mr_label.x", "v3_mr_label.y+26")
            )
            .addComponent(TextureComp("v3_wp_1", "ui/pack/slot_bg.png")
                .setXY("v3_bt.x+10", "v3_lj_label.y+24")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v3_wp_2", "ui/pack/slot_bg.png")
                .setXY("v3_wp_1.x+16+4", "v3_wp_1.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v3_wp_3", "ui/pack/slot_bg.png")
                .setXY("v3_wp_2.x+16+4", "v3_wp_2.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v3_wp_4", "ui/pack/slot_bg.png")
                .setXY("v3_wp_3.x+16+4", "v3_wp_3.y")
                .setWidth("16")
                .setHeight("16")
            )
            .addComponent(TextureComp("v3_gm_bg", "0,0,0,100")
                .setXY("v3_gm.x", "v3_gm.y-16")
                .setWidth("64")
                .setHeight("24")
                .setVisible("func.PlaceholderAPI_Get('ksupporter_svp') == false")
            )
            .addComponent(LabelComp("v3_gm_bg_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_svp') == true ? '' : '&eరె 400,000 神石'")
                .setXY("v3_gm_bg.x+(v3_gm_bg.width-v3_gm_bg_label.width)/2+2", "v3_gm_bg.y+5"))
            .addComponent(TextureComp("v3_gm", "ui/common/button_normal_a.png")
                .setXY("v3.x", "v3_lj_bg.y+45-24")
                .setWidth("64")
                .setHeight("24")
                .addAction(ActionType.Left_Click)
                .addAction(ActionType.Left_Click, Statements()
                    .add("v3_gm.texture = 'ui/common/button_normal_a_press.png';")
                    .build())
                .addAction(ActionType.Left_Release, Statements()
                    .add("v3_gm.texture = 'ui/common/button_normal_a.png';")
                    .build())
            )
            .addComponent(LabelComp("v3_gm_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_mvp') == true ? '&f&l已购买' : '&f&l购买'")
                .setXY("v3_gm.x+(v3_gm.width-v3_gm_label.width)/2+2", "v3_gm.y+6")
            )
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

    @SubscribeEvent
    fun e(e: KeyPressEvent) {
        val player = e.player
        if (e.key == "v1_gm" || e.key == "v2_gm" || e.key == "v3_gm") {
            player.closeInventory()
        } else {
            return
        }
        when (e.key) {
            "v1_gm" -> {
                if (player.hasPermission("vip")) return
                val hasItem = player.inventory.hasItem(1) { it.isSimilar(cardItem) }
                if (!hasItem) {
                    player.closeInventory()
                    MessageAPI.sendActionTip(player, "message-player-failed-buy")
                    return
                }
                player.inventory.takeItem(1) { it.isSimilar(cardItem) }
                giveRewards(player, "vip")
            }
            "v2_gm" -> {
                if (player.hasPermission("svp")) return
                if (!executeDebitFromPlayer(player, 100000.0)) return
                giveRewards(player, "svp")
            }
            "v3_gm" -> {
                if (player.hasPermission("mvp")) return
                if (!executeDebitFromPlayer(player, if (player.hasPermission("svp")) 300000.0 else 400000.0)) {
                    return
                }
                giveRewards(player, "mvp")
            }
            else -> return
        }
    }

    private fun executeDebitFromPlayer(player: Player, value: Double): Boolean {
        if (KirraCoreBukkitAPI.withDraw(player, value, "points")) {
            return true
        }
        MessageAPI.sendActionTip(player, "message-player-failed-buy")
        return false
    }

    private fun giveRewards(player: Player, groupName: String) {
        setGroup(player, groupName)
        val currencyList = KirraSupporter.conf.getStringList("rewards.$groupName.currency") ?: return
        val itemList = KirraSupporter.conf.getStringList("rewards.$groupName.items") ?: return
        currencyList.forEach {
            val split = it.split("@")
            if (split.size < 2) return@forEach
            KirraCoreBukkitAPI.deposit(player, split[1].toDouble(), split[0])
        }
        itemList.forEach {
            val split = it.split("@")
            if (split.size < 2) return@forEach
            val itemData = ZaphkielAPI.getItem(split[0]) ?: return@forEach
            player.giveItem(itemData.rebuildToItemStack(player).also { item ->
                item.amount = split[1].toInt()
            })
        }
        MessageAPI.sendActionTip(player, "player vault_prefix".replacePlaceholder(player))
    }

    private fun setGroup(player: Player, groupName: String) {
        val userData = KirraSupporter.luckPermsAPI.userManager.getUser(player.uniqueId) ?: return
        userData.primaryGroup = groupName
    }
}