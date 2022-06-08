plugins {
    kotlin("jvm") version "+"
    kotlin("plugin.serialization") version "+"
    `java-library`
    `maven-publish`
}

group = "me.senseiju"
version = "0.1.1"

repositories {
    mavenCentral()
    gradlePluginPortal()
    maven("https://hub.spigotmc.org/nexus/content/repositories/snapshots/")
    maven("https://oss.sonatype.org/content/repositories/snapshots")
    maven("https://oss.sonatype.org/content/repositories/central")
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.6.21")
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")
    implementation("com.zaxxer:HikariCP:5.0.1")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.1-native-mt")
    compileOnly("org.spigotmc:spigot-api:1.19-R0.1-SNAPSHOT")

    testImplementation("com.github.seeseemelk:MockBukkit-v1.16:1.5.2")
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.8.2")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine:5.8.2")
}

java {
    withJavadocJar()
    withSourcesJar()
}

publishing {
    publications {
        create<MavenPublication>("mavenJava") {
            artifactId = "sentils"
            from(components["java"])
            versionMapping {
                usage("java-api") {
                    fromResolutionOf("runtimeClasspath")
                }
                usage("java-runtime") {
                    fromResolutionResult()
                }
            }

            pom {
                name.set("Sentils")
                description.set("Library used in SenseiJu's projects")
                url.set("https://senseiju.me")

                developers {
                    developer {
                        id.set("SenseiJu")
                        name.set("Taranjit Chatha")
                        name.set("senseijugaming@gmail.com")
                    }
                }
            }
        }
    }
}

tasks.test {
    useJUnitPlatform()
}