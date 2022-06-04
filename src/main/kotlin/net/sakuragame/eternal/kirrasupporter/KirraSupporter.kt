package net.sakuragame.eternal.kirrasupporter

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.Configuration
import taboolib.platform.BukkitPlugin

@Suppress("SpellCheckingInspection")
object KirraSupporter : Plugin() {

    @Config
    lateinit var conf: Configuration
        private set

    val plugin by lazy {
        BukkitPlugin.getInstance()
    }

    val luckPermsAPI by lazy {
        Bukkit.getServicesManager().getRegistration(LuckPerms::class.java).provider!!
    }
}