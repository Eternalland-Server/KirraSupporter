plugins {
    java
    id("io.izzel.taboolib") version "1.31"
    id("org.jetbrains.kotlin.jvm") version "1.5.30"
}

taboolib {
    description {
        dependencies {
            name("DragonCore")
            name("LuckPerms")
            name("Zaphkiel")
        }
    }
    install("common")
    install("common-5")
    install("module-configuration")
    install("module-chat")
    install("module-lang")
    install("platform-bukkit")
    classifier = null
    version = "6.0.8-5"
}

repositories {
    maven { url = uri("https://repo2s.ptms.ink/repository/maven-releases") }
    mavenCentral()
}

dependencies {
    compileOnly("net.sakuragame.eternal:GemsEconomy:4.9.4-SNAPSHOT@jar")
    compileOnly("ink.ptms:Zaphkiel:1.7.0")
    compileOnly("net.sakuragame.eternal:JustInventory:1.0.2-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:JustMessage:1.0.2-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:KirraCore-Bukkit:1.2.4-SNAPSHOT@jar")
    compileOnly("net.sakuragame.eternal:DragonCore:2.4.8-SNAPSHOT@jar")
    compileOnly("com.taylorswiftcn:UIFactory:1.0.0-SNAPSHOT@jar")
    compileOnly("net.luckperms:api:5.4")
    compileOnly("ink.ptms.core:v11200:11200")
    compileOnly(kotlin("stdlib"))
    compileOnly(fileTree("libs"))
}

tasks.withType<JavaCompile> {
    options.encoding = "UTF-8"
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
}