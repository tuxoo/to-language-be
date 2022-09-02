import com.google.protobuf.gradle.*

buildscript {
    dependencies {
        classpath("com.google.protobuf:protobuf-gradle-plugin:0.8.13")
    }
}

plugins {
    id("org.springframework.boot") version "2.7.2"
    id("io.spring.dependency-management") version "1.0.12.RELEASE"
    id("com.google.protobuf") version "0.8.19"
    kotlin("jvm") version "1.6.21"
    kotlin("plugin.spring") version "1.6.21"
}

group = "com"
version = "0.0.1"
java.sourceCompatibility = JavaVersion.VERSION_17

repositories {
    mavenCentral()
}

sourceSets.main {
    java.srcDirs(
        "build/generated/source/proto/main/java",
        "build/generated/source/proto/main/grpc",
        "build/generated/source/proto/main/grpckt")
}

dependencies {
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
    implementation("org.jetbrains.kotlin:kotlin-stdlib:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-common:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk7:1.5.31")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8:1.5.31")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-jdk8:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactive:1.6.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.6.4")
    implementation("org.springframework.boot:spring-boot-starter-actuator")
    implementation("org.springframework.boot:spring-boot-starter-data-mongodb")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springdoc:springdoc-openapi-ui:1.6.11")
    implementation("io.jsonwebtoken:jjwt-api:0.11.5")
    implementation("org.slf4j:slf4j-api:1.7.36")
    implementation("com.github.ben-manes.caffeine:caffeine:3.1.1")
    implementation("io.grpc:grpc-all:1.49.0")
    implementation("io.grpc:grpc-protobuf:1.49.0")
    implementation("io.grpc:grpc-stub:1.49.0")
    implementation("io.grpc:grpc-netty:1.49.0")
    implementation("io.grpc:protoc-gen-grpc-kotlin:1.3.0")
    implementation("com.google.protobuf:protobuf-gradle-plugin:0.8.19")

    api("com.google.protobuf:protobuf-java-util:3.21.5")
    api("io.grpc:grpc-kotlin-stub:1.3.0")

    runtimeOnly("io.jsonwebtoken:jjwt-impl:0.11.5")
    runtimeOnly("io.jsonwebtoken:jjwt-jackson:0.11.5")

    testImplementation("io.projectreactor:reactor-test")
    testImplementation("org.springframework.boot:spring-boot-starter-test")
    testImplementation("org.springframework.security:spring-security-test")
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.10.1"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:1.33.1"
        }
        id("grpckt") {
            artifact = "io.grpc:protoc-gen-grpc-kotlin:0.1.5"
        }
    }

    generateProtoTasks {
        all().forEach {
            it.plugins {
                id("grpc")
                id("grpckt")
            }
        }
    }
}

tasks.compileKotlin {
    dependsOn(":generateProto")
}