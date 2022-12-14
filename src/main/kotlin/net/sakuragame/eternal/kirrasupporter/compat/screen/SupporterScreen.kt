package net.sakuragame.eternal.kirrasupporter.compat.screen

import com.taylorswiftcn.megumi.uifactory.generate.function.Statements
import com.taylorswiftcn.megumi.uifactory.generate.type.ActionType
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.LabelComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.component.base.TextureComp
import com.taylorswiftcn.megumi.uifactory.generate.ui.screen.ScreenUI
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake


object SupporterScreen {

    lateinit var UI: ScreenUI

    @Awake(LifeCycle.ENABLE)
    fun i() {
        UI = ScreenUI("supporter")
            .setMatch("hud")
            .addImports(listOf("vip", "mvp", "svp"))
            .addComponent(
                TextureComp("body", "0,0,0,0")
                    .setXY("(w-body.width)/2", "(h-body.height)/2")
                    .setWidth("810")
                    .setHeight("300")
            )
            .addComponent(
                TextureComp("v1", "0,0,0,0")
                    .setXY("body.x", "body.y+80")
                    .setWidth("")
                    .setHeight("")
            )
            .addComponent(
                TextureComp("v2", "0,0,0,0")
                    .setXY("v1.x+270", "v1.y")
                    .setWidth("")
                    .setHeight("")
            )
            .addComponent(
                TextureComp("v3", "0,0,0,0")
                    .setXY("v2.x+270", "v2.y")
                    .setWidth("")
                    .setHeight("")
            )
            .addComponent(
                TextureComp("npc_1", "ui/support/11.png")
                    .setXY("v1.x", "body.y+23")
                    .setWidth("66")
                    .setHeight("90")
            )
            .addComponent(
                TextureComp("npc_2", "ui/support/12.png")
                    .setXY("v2.x", "body.y+23")
                    .setWidth("69")
                    .setHeight("90")
            )
            .addComponent(
                TextureComp("npc_3", "ui/support/13.png")
                    .setXY("v3.x", "body.y+23")
                    .setWidth("63")
                    .setHeight("90")
            )
            .addComponent(
                TextureComp("adorn_1", "ui/common/quest_option_adorn.png")
                    .setXY("npc_1.x+65", "v1.y-30")
                    .setWidth("11")
                    .setHeight("26")
            )
            .addComponent(
                TextureComp("adorn_2", "ui/common/quest_option_adorn.png")
                    .setXY("npc_2.x+65", "v2.y-30")
                    .setWidth("11")
                    .setHeight("26")
            )
            .addComponent(
                TextureComp("adorn_3", "ui/common/quest_option_adorn.png")
                    .setXY("npc_3.x+65", "v3.y-30")
                    .setWidth("11")
                    .setHeight("26")
            )
            .addComponent(
                TextureComp("frame_1", "ui/common/black_gradient_vip.png")
                    .setXY("adorn_1.x+13", "adorn_1.y-16")
                    .setWidth("162")
                    .setHeight("60")
            )
            .addComponent(
                TextureComp("frame_2", "ui/common/black_gradient_vip.png")
                    .setXY("adorn_2.x+13", "adorn_2.y-16")
                    .setWidth("162")
                    .setHeight("60")
            )
            .addComponent(
                TextureComp("frame_3", "ui/common/black_gradient_vip.png")
                    .setXY("adorn_3.x+13", "adorn_3.y-16")
                    .setWidth("162")
                    .setHeight("60")
            )
            .addComponent(
                LabelComp("tips_1", "")
                    .setTexts(Statements()
                        .add("&a????????????: ")
                        .add("&f??????????????????????????????, ???????????????!")
                        .add("&f???????????????????????????.")
                        .add("")
                        .add("&7&o????????????????????????, ???????????????.")
                        .build())
                    .setXY("frame_1.x+5", "frame_1.y+5")
            )
            .addComponent(
                LabelComp("tips_2", "")
                    .setTexts(Statements()
                        .add("&a????????????: ")
                        .add("&f???????????????????????????????????????????????????.")
                        .add("&f???????????????????????????.")
                        .add("")
                        .add("&7&o????????????????????????, ???????????????.")
                        .build())
                    .setXY("frame_2.x+5", "frame_2.y+5")
            )
            .addComponent(
                LabelComp("tips_3", "")
                    .setTexts(Statements()
                        .add("&a????????????: ")
                        .add("&f???????????????????????????????????????????????????.")
                        .add("&f???????????????????????????.")
                        .add("")
                        .add("&7&o????????????????????????, ???????????????.")
                        .build())
                    .setXY("frame_3.x+5", "frame_3.y+5")
            )
    }
}