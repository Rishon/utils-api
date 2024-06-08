# utils-api

## Introduction

The `utils-api` is a toolkit for Minecraft plugin development, offering essential utilities to simplify common tasks, enhance functionality, and accelerate plugin creation.

## Features

- **Serialization:** Easily serialize and deserialize Minecraft items for storage or transfer.
- **Item Manipulation:** Simplify item creation, modification, and management within your plugins.
- **Color Handling:** Integrate colorful text and item names effortlessly into your Minecraft plugins.
- and more...

## Usage

To integrate `utils-api` into your project, add the following Maven repository and dependency declarations to your `pom.xml` file:

```xml
<repository>
  <id>seladevelopment-releases</id>
  <name>Sela Development Repository</name>
  <url>https://repo.rishon.systems/releases</url>
</repository>

<dependency>
  <groupId>systems.rishon</groupId>
  <artifactId>utils-api</artifactId>
  <version>1.0.0</version>
</dependency>
```

For Gradle users, add the following repository and dependency declaration to your `build.gradle.kts` file:

```gradle
repositories {
    maven {
        name = "seladevelopmentReleases"
        url = uri("https://repo.rishon.systems/releases")
    }
}

dependencies {
    implementation("systems.rishon:utils-api:1.0.0")
}
```
