plugins {
    kotlin("jvm") version "2.0.0"
    id("io.papermc.paperweight.userdev") version "1.7.4"
    id("maven-publish")
}

group = "systems.rishon"
version = "1.0.1"

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
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.12.4")
    compileOnly("org.geysermc.floodgate:api:2.2.3-SNAPSHOT")

    paperweight.paperDevBundle("1.20.6-R0.1-SNAPSHOT")
}

kotlin {
    jvmToolchain(21)
}

publishing {
    repositories {
        maven {
            name = "seladevelopment-repo"
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