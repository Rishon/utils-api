plugins {
    kotlin("jvm") version "2.0.0"
    id("io.papermc.paperweight.userdev") version "1.7.1"
    id("maven-publish")
}

group = "systems.rishon"
version = "1.0.0"

repositories {
    mavenCentral()
    maven {
        name = "CodeMC"
        url = uri("https://repo.codemc.io/repository/maven-public/")
    }
}

dependencies {
    compileOnly("de.tr7zw:item-nbt-api-plugin:2.12.4")

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