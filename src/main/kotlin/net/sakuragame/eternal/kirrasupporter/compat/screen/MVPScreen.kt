package net.sakuragame.eternal.kirrasupporter.compat.screen

import com.taylorswiftcn.megumi.uifactory.generate.function.Statements
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.SlotComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import net.sakuragame.eternal.kirrasupporter.compat.FunctionScreen
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.module.chat.colored

object MVPScreen {

    val rewards by lazy {
        FunctionScreen.generateList("mvp")
    }

    lateinit var UI: ScreenUI

    @Awake(LifeCycle.ENABLE)
    fun i() {
        UI = ScreenUI("mvp")
            .addComponent(
                TextureComp("mvp_bg", "ui/support/background.png")
                    .setXY("v3.x", "v3.y")
                    .setWidth("270")
                    .setHeight("220")
                    .setAlpha(0.9)
            )
            .addComponent(
                TextureComp("mvp_card", "ui/common/text_background.png")
                    .setText("&b&l钻石会员&c (永久)".colored())
                    .setXY("mvp_bg.x+16", "mvp_bg.y+30")
                    .setWidth("108")
                    .setHeight("20")
            )
            .addComponent(
                TextureComp("mvp_frame", "ui/common/color_bg_frame_c.png")
                    .setXY("mvp_bg.x+16", "mvp_card.y+35")
                    .setWidth("162")
                    .setHeight("64")
            )
            .addComponent(
                LabelComp("mvp_desc", "")
                    .setTexts(Statements()
                        .add("&a部分特权介绍: ")
                        .add("&7· &f大部分副本无限进入, 副本次数提升.")
                        .add("&7· &f商场获得 30% 折扣.")
                        .add("&7· &f³ 专属称号.")
                        .add("&7· &f每日会员礼包. &b&l超级款!")
                        .build())
                    .setXY("mvp_frame.x+8", "mvp_frame.y+8")
            )
            .addComponent(
                TextureComp("mvp_icon", "ui/support/2.png")
                    .setXY("mvp_bg.x+193", "mvp_bg.y+17")
                    .setWidth("64")
                    .setHeight("64")
            )
            .addComponent(
                TextureComp("mvp_daily_frame", "ui/common/black_gradient_vip.png")
                    .setXY("mvp_frame.x", "mvp_frame.y+68")
                    .setWidth("162")
                    .setHeight("23")
            )
            .addComponent(
                LabelComp("mvp_daily_desc", "")
                    .setTexts(Statements()
                        .add("&f&l每日礼包: ")
                        .add("&eΓx30,000,000, &e☪x300,000, &e㊉x3,000  &cβx10")
                        .build())
                    .setXY("mvp_daily_frame.x+6", "mvp_daily_frame.y+3")
            )
            .addComponent(
                TextureComp("mvp_reward_frame", "ui/common/golden_gradient_vip.png")
                    .setXY("mvp_daily_frame.x", "mvp_daily_frame.y+26")
                    .setWidth("162")
                    .setHeight("45")
                    .setAlpha(0.7)
            )
            .addComponent(
                LabelComp("mvp_reward_desc", "")
                    .setTexts(Statements()
                        .add("&f&l购买后立即获得: ")
                        .add("&eΓx500,000,000, &e☪x3,000,000 , &cβx10")
                        .build())
                    .setXY("mvp_reward_frame.x+5", "mvp_reward_frame.y+3")
            )
            .addComponent(
                TextureComp("mvp_iframe_1", "ui/pack/slot_bg.png")
                    .setXY("mvp_reward_frame.x+6", "mvp_reward_frame.y+24")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("mvp_iframe_2", "ui/pack/slot_bg.png")
                    .setXY("mvp_iframe_1.x+22", "mvp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("mvp_iframe_3", "ui/pack/slot_bg.png")
                    .setXY("mvp_iframe_2.x+22", "mvp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("mvp_iframe_4", "ui/pack/slot_bg.png")
                    .setXY("mvp_iframe_3.x+22", "mvp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("mvp_iframe_5", "ui/pack/slot_bg.png")
                    .setXY("mvp_iframe_4.x+22", "mvp_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                SlotComp("mvp_item_1", "mvp_item_1")
                    .setDrawBackground(false)
                    .setXY("mvp_iframe_1.x+1", "mvp_iframe_1.y+1")
            )
            .addComponent(
                SlotComp("mvp_item_2", "mvp_item_2")
                    .setDrawBackground(false)
                    .setXY("mvp_iframe_2.x+1", "mvp_iframe_2.y+1")
            )
            .addComponent(
                SlotComp("mvp_item_3", "mvp_item_3")
                    .setDrawBackground(false)
                    .setXY("mvp_iframe_3.x+1", "mvp_iframe_3.y+1")
            )
            .addComponent(
                SlotComp("mvp_item_4", "mvp_item_4")
                    .setDrawBackground(false)
                    .setXY("mvp_iframe_4.x+1", "mvp_iframe_4.y+1")
            )
            .addComponent(
                SlotComp("mvp_item_5", "mvp_item_5")
                    .setDrawBackground(false)
                    .setXY("mvp_iframe_5.x+1", "mvp_iframe_5.y+1")
            )
            .addComponent(TextureComp("mvp_buy_bg", "0,0,0,100")
                .setXY("mvp_buy.x", "mvp_buy.y-16")
                .setWidth("64")
                .setHeight("24")
                .setVisible("func.PlaceholderAPI_Get('ksupporter_mvp') == false")
            )
            .addComponent(
                TextureComp("mvp_buy", "ui/common/button_normal_a.png")
                    .setXY("mvp_icon.x", "mvp_reward_frame.y+21")
                    .setWidth("64")
                    .setHeight("24")
                    .addAction(ActionType.Left_Click)
                    .addAction(
                        ActionType.Left_Click, Statements()
                            .add("mvp_buy.texture = 'ui/common/button_normal_a_press.png';")
                            .build())
                    .addAction(
                        ActionType.Left_Release, Statements()
                            .add("mvp_buy.texture = 'ui/common/button_normal_a.png';")
                            .build())
            )
            .addComponent(LabelComp("mvp_buy_bg_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_mvp') == true ? '' : '&eరె 100,000 神石'")
                .setXY("mvp_buy_bg.x+(mvp_buy.width-mvp_buy_bg_label.width)/2+2", "mvp_buy_bg.y+5")
            )
            .addComponent(LabelComp("mvp_buy_label", "")
                .setTexts("func.PlaceholderAPI_Get('ksupporter_mvp') == true ? '&f&l已购买' : '&f&l购买'")
                .setXY("mvp_buy.x+(mvp_buy.width-mvp_buy_label.width)/2+2", "mvp_buy.y+6")
            )
    }
}