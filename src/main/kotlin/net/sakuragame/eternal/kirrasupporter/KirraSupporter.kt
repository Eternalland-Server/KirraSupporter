package net.sakuragame.eternal.kirrasupporter

import net.luckperms.api.LuckPerms
import org.bukkit.Bukkit
import taboolib.common.platform.Plugin
import taboolib.module.configuration.Config
import taboolib.module.configuration.SecuredFile


object KirraSupporter : Plugin() {

    @Config
    lateinit var conf: SecuredFile
        private set

    val luckPermsAPI by lazy {
        Bukkit.getServicesManager().getRegistration(LuckPerms::class.java).provider!!
    }
}