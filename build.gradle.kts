
import io.spring.gradle.dependencymanagement.dsl.DependencyManagementExtension

val kotlinVersion: String by project
val springBootVersion: String by project
val springCloudVersion: String by project
val springCloudSecurityVersion: String by project
val weMavenUser: String by project
val weMavenPassword: String by project
val jnaVersion: String by project
val jacksonKotlinVersion: String by project
val jacksonDatabindVersion: String by project
val postgresVersion: String by project
val feignCoreVersion: String by project
val springdocVersion: String by project

val weSdkBomVersion: String by project

plugins {
    kotlin("jvm") apply false
    kotlin("plugin.spring") apply false
    kotlin("plugin.jpa") apply false
    id("org.springframework.boot") apply false
    id("io.spring.dependency-management") apply false
    `maven-publish`
}

allprojects {
    group = "com.wavesenterprise.app"
    version = "1.0.0-SNAPSHOT"

    repositories {
        mavenCentral()
        maven {
            name = "maven-snapshots"
            url = uri("https://artifacts.wavesenterprise.com/repository/maven-snapshots/")
            mavenContent {
                snapshotsOnly()
            }
            credentials {
                username = weMavenUser
                password = weMavenPassword
            }
        }

        maven {
            name = "maven-releases"
            url = uri("https://artifacts.wavesenterprise.com/repository/maven-releases/")
            mavenContent {
                releasesOnly()
            }
            credentials {
                username = weMavenUser
                password = weMavenPassword
            }
        }
        mavenLocal()
    }
}

subprojects {
    apply(plugin = "io.spring.dependency-management")
    apply(plugin = "kotlin")

    the<DependencyManagementExtension>().apply {
        imports {
            mavenBom("org.springframework.boot:spring-boot-dependencies:$springBootVersion") {
                bomProperty("kotlin.version", kotlinVersion)
            }
            mavenBom("com.wavesenterprise:we-sdk-bom:$weSdkBomVersion") {
                bomProperty("kotlin.version", kotlinVersion)
            }
            mavenBom("com.fasterxml.jackson:jackson-bom:$jacksonKotlinVersion")
        }

        dependencies {
            dependency("com.fasterxml.jackson.datatype:jackson-datatype-jsr310:$jacksonDatabindVersion")
            dependency("com.fasterxml.jackson.module:jackson-module-kotlin:$jacksonKotlinVersion")
            dependency("net.java.dev.jna:jna:$jnaVersion")

            dependency("org.postgresql:postgresql:$postgresVersion")
            dependency("org.springdoc:springdoc-openapi-ui:$springdocVersion")
        }
    }

    val ENABLE_PREVIEW = "--enable-preview"
    tasks.withType<JavaCompile> {
        options.compilerArgs.add(ENABLE_PREVIEW)
    }
}