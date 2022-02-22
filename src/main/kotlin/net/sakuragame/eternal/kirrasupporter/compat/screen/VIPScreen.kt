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


@Suppress("SpellCheckingInspection")
object VIPScreen {

    val rewards by lazy {
        FunctionScreen.generateList("vip")
    }

    lateinit var UI: ScreenUI

    @Awake(LifeCycle.ENABLE)
    fun i() {
        UI = ScreenUI("vip")
            .addComponent(
                TextureComp("vip_bg", "ui/support/background.png")
                    .setXY("v1.x", "v1.y")
                    .setWidth("270")
                    .setHeight("220")
                    .setAlpha(0.9)
            )
            .addComponent(
                TextureComp("vip_card", "ui/common/text_background.png")
                    .setText("&f&l白银会员&c (永久)".colored())
                    .setXY("vip_bg.x+16", "vip_bg.y+30")
                    .setWidth("108")
                    .setHeight("20")
            )
            .addComponent(
                TextureComp("vip_frame", "ui/common/color_bg_frame_c.png")
                    .setXY("vip_bg.x+16", "vip_card.y+35")
                    .setWidth("162")
                    .setHeight("64")
            )
            .addComponent(
                LabelComp("vip_desc", "")
                    .setTexts(
                        Statements()
                            .add("&a部分特权介绍: ")
                            .add("&7· &f进入副本次数提升.")
                            .add("&7· &f商场获得 10% 折扣.")
                            .add("&7· &f² 专属称号.")
                            .add("&7· &f每日会员礼包.")
                            .build()
                    )
                    .setXY("vip_frame.x+8", "vip_frame.y+8")
            )
            .addComponent(
                TextureComp("vip_icon", "ui/support/1.png")
                    .setXY("vip_bg.x+193", "vip_bg.y+17")
                    .setWidth("64")
                    .setHeight("64")
            )
            .addComponent(
                TextureComp("vip_daily_frame", "ui/common/black_gradient_vip.png")
                    .setXY("vip_frame.x", "vip_frame.y+68")
                    .setWidth("162")
                    .setHeight("23")
            )
            .addComponent(
                LabelComp("vip_daily_desc", "")
                    .setTexts(
                        Statements()
                            .add("&f&l每日礼包: ")
                            .add("&eΓx5,000,000, &e☪x50,000, &cβx1")
                            .build()
                    )
                    .setXY("vip_daily_frame.x+6", "vip_daily_frame.y+3")
            )
            .addComponent(
                TextureComp("vip_reward_frame", "ui/common/golden_gradient_vip.png")
                    .setXY("vip_daily_frame.x", "vip_daily_frame.y+26")
                    .setWidth("162")
                    .setHeight("45")
                    .setAlpha(0.7)
            )
            .addComponent(
                LabelComp("vip_reward_desc", "")
                    .setTexts(
                        Statements()
                            .add("&f&l购买后立即获得: ")
                            .add("&eΓx50,000,000, &e☪x500,000, &cβx10")
                            .build()
                    )
                    .setXY("vip_reward_frame.x+5", "vip_reward_frame.y+3")
            )
            .addComponent(
                TextureComp("vip_iframe_1", "ui/pack/slot_bg.png")
                    .setXY("vip_reward_frame.x+6", "vip_reward_frame.y+24")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("vip_iframe_2", "ui/pack/slot_bg.png")
                    .setXY("vip_iframe_1.x+22", "vip_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("vip_iframe_3", "ui/pack/slot_bg.png")
                    .setXY("vip_iframe_2.x+22", "vip_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("vip_iframe_4", "ui/pack/slot_bg.png")
                    .setXY("vip_iframe_3.x+22", "vip_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                TextureComp("vip_iframe_5", "ui/pack/slot_bg.png")
                    .setXY("vip_iframe_4.x+22", "vip_iframe_1.y")
                    .setWidth("18")
                    .setHeight("18")
            )
            .addComponent(
                SlotComp("vip_item_1", "vip_item_1")
                    .setDrawBackground(false)
                    .setXY("vip_iframe_1.x+1", "vip_iframe_1.y+1")
            )
            .addComponent(
                SlotComp("vip_item_2", "vip_item_2")
                    .setDrawBackground(false)
                    .setXY("vip_iframe_2.x+1", "vip_iframe_2.y+1")
            )
            .addComponent(
                SlotComp("vip_item_3", "vip_item_3")
                    .setDrawBackground(false)
                    .setXY("vip_iframe_3.x+1", "vip_iframe_3.y+1")
            )
            .addComponent(
                SlotComp("vip_item_4", "vip_item_4")
                    .setDrawBackground(false)
                    .setXY("vip_iframe_4.x+1", "vip_iframe_4.y+1")
            )
            .addComponent(
                SlotComp("vip_item_5", "vip_item_5")
                    .setDrawBackground(false)
                    .setXY("vip_iframe_5.x+1", "vip_iframe_5.y+1")
            )
            .addComponent(
                TextureComp("vip_buy_bg", "0,0,0,100")
                    .setXY("vip_buy.x", "vip_buy.y-16")
                    .setWidth("64")
                    .setHeight("24")
                    .setVisible("func.PlaceholderAPI_Get('ksupporter_vip') == false")
            )
            .addComponent(
                LabelComp("vip_buy_bg_label", "")
                    .setTexts("func.PlaceholderAPI_Get('ksupporter_vip') == true ? '' : '&e抵用券 x 1'")
                    .setXY("vip_buy_bg.x+(vip_buy_bg.width-vip_buy_bg_label.width)/2+2", "vip_buy_bg.y+5")
            )
            .addComponent(
                TextureComp("vip_buy", "ui/common/button_normal_a.png")
                    .setXY("vip_icon.x", "vip_reward_frame.y+21")
                    .setWidth("64")
                    .setHeight("24")
                    .addAction(ActionType.Left_Click)
                    .addAction(
                        ActionType.Left_Click, Statements()
                            .add("vip_buy.texture = 'ui/common/button_normal_a_press.png';")
                            .build()
                    )
                    .addAction(
                        ActionType.Left_Release, Statements()
                            .add("vip_buy.texture = 'ui/common/button_normal_a.png';")
                            .build()
                    )
            )
            .addComponent(
                LabelComp("vip_buy_label", "")
                    .setTexts("func.PlaceholderAPI_Get('ksupporter_vip') == true ? '&f&l已购买' : '&f&l购买'")
                    .setXY("vip_buy.x+(vip_buy.width-vip_buy_label.width)/2+2", "vip_buy.y+6")
            )
    }
}