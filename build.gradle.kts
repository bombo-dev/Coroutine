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
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.13.3")
    testImplementation("org.junit.jupiter:junit-jupiter-engine:5.13.3")
    testImplementation("org.assertj:assertj-core:3.27.3")
    testImplementation("org.jetbrains.kotlinx:kotlinx-coroutines-test:${project.ext["coroutineVersion"]}")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}
kotlin {
    jvmToolchain(21)
}