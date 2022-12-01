plugins {
    kotlin("jvm") version "1.7.22"
    id("org.jmailen.kotlinter") version "3.12.0"
}

repositories {
    mavenCentral()
}

tasks {
    sourceSets {
        main {
            java.srcDirs("src")
        }
    }

    wrapper {
        gradleVersion = "7.6"
    }
}
