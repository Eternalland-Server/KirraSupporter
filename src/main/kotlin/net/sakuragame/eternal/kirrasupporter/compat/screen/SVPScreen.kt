package net.sakuragame.eternal.kirrasupporter.compat.screen

import com.taylorswiftcn.megumi.uifactory.generate.function.Statements
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import net.sakuragame.eternal.kirrasupporter.compat.FunctionScreen
import org.bukkit.configuration.file.YamlConfiguration
import org.bukkit.entity.Player
import taboolib.module.chat.colored


@Suppress("SpellCheckingInspection")
object SVPScreen {

    val rewards by lazy {
        FunctionScreen.generateList("svp")
    }

    fun build(player: Player): YamlConfiguration {
        return ScreenUI("svp").apply {
            addComponent(
                TextureComp("svp_bg", "ui/support/background.png")
                    .setXY("v2.x", "v2.y")
                    .setWidth("270")
                    .setHeight("220")
                    .setAlpha(0.9)
            )
            addComponent(
                TextureComp("svp_card", "ui/common/text_background.png")
                    .setText("&6&l铂金会员&c (永久)".colored())
                    .setXY("svp_bg.x+16", "svp_bg.y+30")
                    .setWidth("108")
                    .setHeight("20")
                    .setScale(1.5)
            )
            addComponent(
                TextureComp("svp_frame", "ui/common/color_bg_frame_c.png")
                    .setXY("svp_bg.x+16", "svp_card.y+35")
                    .setWidth("162")
                    .setHeight("64")
            )
            addComponent(
                LabelComp("svp_desc", "")
                    .setTexts(Statements()
                        .add("&a部分特权介绍: ")
                        .add("&7· &f部分副本无限进入, 副本次数提升.")
                        .add("&7· &f商场获得 20% 折扣.")
                        .add("&7· &f² 专属称号.")
                        .add("&7· &f每日会员礼包. &6&l升级款!")
                        .build())
                    .setXY("svp_frame.x+8", "svp_frame.y+8")
            )
            addComponent(
                TextureComp("svp_icon", "ui/support/2.png")
                    .setXY("svp_bg.x+193", "svp_bg.y+17")
                    .setWidth("64")
                    .setHeight("64")
            )
            addComponent(
                TextureComp("svp_daily_frame", "ui/common/black_gradient_vip.png")
                    .setXY("svp_frame.x", "svp_frame.y+68")
                    .setWidth("162")
                    .setHeight("23")
            )
            addComponent(
                LabelComp("svp_daily_desc", "")
                    .setTexts(Statements()
                        .add("&f&l每日签到额外奖励: ")
                        .add("&eΓx5,000,000, &e☪x100,000, &e㊉x1,000, &cβx3.")
                        .build())
                    .setXY("svp_daily_frame.x+6", "svp_daily_frame.y+3")
            )
            addComponent(
                TextureComp("svp_reward_frame", "ui/common/golden_gradient_vip.png")
                    .setXY("svp_daily_frame.x", "svp_daily_frame.y+26")
                    .setWidth("162")
                    .setHeight("45")
                    .setAlpha(0.7)
            )
            addComponent(
                LabelComp("svp_reward_desc", "")
                    .setTexts(Statements()
                        .add("&f&l购买后立即获得: ")
                        .add("&eΓx 100,000,000, &e☪x1,000,000, &cβx30")
                        .build())
                    .setXY("svp_reward_frame.x+5", "svp_reward_frame.y+3")
            )
            addComponent(
                TextureComp("svp_iframe_1", "ui/pack/slot_bg.png")
                    .setXY("svp_reward_frame.x+6", "svp_reward_frame.y+24")
                    .setWidth("18")
                    .setHeight("18")
            )
            addComponent(
                TextureComp("svp_iframe_2", "ui/pack/slot_bg.png")
                    .setXY("svp_iframe_1.x+22", "svp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            addComponent(
                TextureComp("svp_iframe_3", "ui/pack/slot_bg.png")
                    .setXY("svp_iframe_2.x+22", "svp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            addComponent(
                TextureComp("svp_iframe_4", "ui/pack/slot_bg.png")
                    .setXY("svp_iframe_3.x+22", "svp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            addComponent(
                TextureComp("svp_iframe_5", "ui/pack/slot_bg.png")
                    .setXY("svp_iframe_4.x+22", "svp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            addComponent(
                SlotComp("svp_item_1", "svp_item_1")
                    .setDrawBackground(false)
                    .setXY("svp_iframe_1.x+1", "svp_iframe_1.y+1")
            )
            addComponent(
                SlotComp("svp_item_2", "svp_item_2")
                    .setDrawBackground(false)
                    .setXY("svp_iframe_2.x+1", "svp_iframe_2.y+1")
            )
            addComponent(
                SlotComp("svp_item_3", "svp_item_3")
                    .setDrawBackground(false)
                    .setXY("svp_iframe_3.x+1", "svp_iframe_3.y+1")
            )
            addComponent(
                SlotComp("svp_item_4", "svp_item_4")
                    .setDrawBackground(false)
                    .setXY("svp_iframe_4.x+1", "svp_iframe_4.y+1")
            )
            addComponent(
                SlotComp("svp_item_5", "svp_item_5")
                    .setDrawBackground(false)
                    .setXY("svp_iframe_5.x+1", "svp_iframe_5.y+1")
            )
            addComponent(TextureComp("svp_buy_bg", "0,0,0,100")
                .setXY("svp_buy.x", "svp_buy.y-16")
                .setWidth("64")
                .setHeight("24")
                .setVisible("func.PlaceholderAPI_Get('ksupporter_svp') == false")
            )
            addComponent(LabelComp("svp_buy_bg_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_svp') == true ? '' : '&e㊉ 100,000 神石'")
                .setXY("svp_buy_bg.x+(svp_buy.width-svp_buy_bg_label.width)/2+2", "svp_buy_bg.y+5")
            )
            if (player.hasPermission("svp") || player.hasPermission("mvp")) {
                addComponent(TextureComp("svp_cant_buy", "ui/common/button_normal_g.png")
                    .setText("&f&l已购买")
                    .setXY("svp_icon.x", "svp_reward_frame.y+21")
                    .setWidth("64")
                    .setHeight("24")
                )
            } else {
                addComponent(
                    TextureComp("svp_buy", "ui/common/button_normal_a.png")
                        .setText("&f&l购买")
                        .setXY("svp_icon.x", "svp_reward_frame.y+21")
                        .setWidth("64")
                        .setHeight("24")
                        .addAction(ActionType.Left_Click)
                        .addAction(
                            ActionType.Left_Click, Statements()
                                .add("svp_buy.texture = 'ui/common/button_normal_a_press.png';")
                                .build())
                        .addAction(
                            ActionType.Left_Release, Statements()
                                .add("svp_buy.texture = 'ui/common/button_normal_a.png';")
                                .build()
                        )
                )
            }
        }.build(player)
    }
}