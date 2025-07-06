plugins {
    kotlin("jvm") version "2.1.21"
}

group = "com.bombo"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

ext {
    set("coroutineVersion", "1.10.2")
}

dependencies {
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:${project.ext["coroutineVersion"]}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}