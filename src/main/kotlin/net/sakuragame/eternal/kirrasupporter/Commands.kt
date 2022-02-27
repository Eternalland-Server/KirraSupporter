package net.sakuragame.eternal.kirrasupporter

import net.sakuragame.eternal.dragoncore.config.FolderType
import net.sakuragame.eternal.dragoncore.network.PacketSender
import net.sakuragame.eternal.kirrasupporter.compat.*
import net.sakuragame.eternal.kirrasupporter.compat.screen.MVPScreen
import net.sakuragame.eternal.kirrasupporter.compat.screen.SVPScreen
import net.sakuragame.eternal.kirrasupporter.compat.screen.SupporterScreen
import net.sakuragame.eternal.kirrasupporter.compat.screen.VIPScreen
import org.bukkit.entity.Player
import taboolib.common.LifeCycle
import taboolib.common.platform.Awake
import taboolib.common.platform.command.PermissionDefault
import taboolib.common.platform.command.command

object Commands {

    @Awake(LifeCycle.ACTIVE)
    fun init() {
        command("buy-vip", permissionDefault = PermissionDefault.TRUE) {
            execute<Player> { player, _, _ ->
                PacketSender.sendYaml(player, FolderType.Gui, "supporter", SupporterScreen.UI.build(player))
                PacketSender.sendYaml(player, FolderType.Gui, "vip", VIPScreen.build(player))
                PacketSender.sendYaml(player, FolderType.Gui, "svp", SVPScreen.build(player))
                PacketSender.sendYaml(player, FolderType.Gui, "mvp", MVPScreen.build(player))
                PacketSender.sendOpenGui(player, "supporter")
                FunctionScreen.sendSlotItems(player)
                FunctionScreen.updateVars(player)
            }
        }
    }
}