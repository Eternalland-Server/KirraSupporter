package net.sakuragame.eternal.kirrasupporter

import com.taylorswiftcn.megumi.uifactory.generate.ui.UIFactory
import net.sakuragame.eternal.kirrasupporter.compat.DragonCore
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.command.command

object Commands {

    @Awake(LifeCycle.ACTIVE)
    fun init() {
        command("buy-vip") {
            execute<Player> { player, _, _ ->
                UIFactory.open(player, DragonCore.shopUI)
                DragonCore.updateVars(player)
            }
        }
    }
}