plugins {
    kotlin("jvm") version "2.2.10"
    id("io.papermc.paperweight.userdev") version "1.7.7"
    id("maven-publish")
}

group = "systems.rishon"
version = "1.0.2"

repositories {
    gradlePluginPortal()
    mavenCentral()
    maven {
        name = "CodeMC"
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
    maven {
        url = uri("https://repo.opencollab.dev/main/")
    }
}

dependencies {
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.15.2")
    compileOnly("org.geysermc.floodgate:api:2.2.4-SNAPSHOT")

    paperweight.paperDevBundle("1.21.1-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    repositories {
        maven {
            name = "seladevelopment"
            url = uri("https://repo.rishon.systems/releases")
            credentials {
                username = System.getenv("MAVEN_NAME")
                password = System.getenv("MAVEN_SECRET")
            }
            authentication {
                create<BasicAuthentication>("basic")
            }
        }
    }
    publications {
        create<MavenPublication>("maven") {
            groupId = "systems.rishon"
            artifactId = "utils-api"
            version = "${project.version}"
            from(components["java"])
        }
    }
}