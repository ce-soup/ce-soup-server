// grpc
import com.google.protobuf.gradle.*
import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

val protobufJavaVersion = "3.20.1"
val grpcVersion = "1.46.0"
val grpcStarterVersion = "2.13.1.RELEASE"
val queryDslVersion = "5.0.0"

plugins {
    id("org.springframework.boot") version "2.6.7"
    id("io.spring.dependency-management") version "1.0.11.RELEASE"

    //grpc (protocol buffer 파일을 .java 파일로 만들어주는 protoc를 빌드 도구에 추가)
    id("com.google.protobuf") version "0.8.17"

    kotlin("jvm") version "1.6.20"
    kotlin("plugin.spring") version "1.6.20"
    kotlin("plugin.jpa") version "1.6.20"

    // allopen & noarg
    kotlin("plugin.allopen") version "1.3.71"
    kotlin("plugin.noarg") version "1.3.71"

    // serialization
    kotlin("plugin.serialization") version "1.5.0"

    // kapt for QueryDSL
    kotlin("kapt") version "1.4.10"
    idea
}

// allopen
allOpen {
    annotation("javax.persistence.Entity")
    annotation("javax.persistence.MappedSuperclass")
}

// noarg
noArg {
    annotation("javax.persistence.Entity")
}

group = "kr.soupio"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_11

repositories {
    mavenCentral()
}

extra["springCloudVersion"] = "2021.0.1"

dependencies {
    implementation("org.springframework.boot:spring-boot-starter-data-jpa")
    implementation("org.springframework.boot:spring-boot-starter-data-redis")
    implementation("org.springframework.boot:spring-boot-starter-security")
    implementation("org.springframework.boot:spring-boot-starter-validation")
    implementation("org.springframework.boot:spring-boot-starter-web")
    implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
    implementation("org.jetbrains.kotlin:kotlin-reflect")
    implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
    implementation("org.springframework.cloud:spring-cloud-starter-netflix-eureka-client")
    runtimeOnly("mysql:mysql-connector-java")
    testImplementation("org.springframework.boot:spring-boot-starter-test")

    // swagger
    implementation("io.springfox:springfox-boot-starter:3.0.0")

    // grpc
    implementation("com.google.protobuf:protobuf-java:$protobufJavaVersion")
    implementation("io.grpc:grpc-protobuf:$grpcVersion")
    implementation("io.grpc:grpc-netty:$grpcVersion")
    implementation("net.devh:grpc-spring-boot-starter:$grpcStarterVersion")
    implementation("net.devh:grpc-server-spring-boot-starter:$grpcStarterVersion")

    // serialization
    implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.3")

    // minio
    implementation("io.minio:minio:8.2.1")

    // QueryDSL
    implementation("com.querydsl:querydsl-jpa:$queryDslVersion")
    kapt("com.querydsl:querydsl-apt:$queryDslVersion:jpa")
    kapt("org.springframework.boot:spring-boot-configuration-processor")
}

idea {
    module {
        val kaptMain = file("build/generated/source/kapt/main")
        sourceDirs.add(kaptMain)
        generatedSourceDirs.add(kaptMain)
    }
}

sourceSets {
    getByName("main") {
        java {
            srcDirs(
                "build/generated/source/proto/main/grpc",
                "build/generated/source/proto/main/java"
            )
        }
    }
}

protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:${protobufJavaVersion}"
    }

    plugins {
        id("grpc") {
            artifact = "io.grpc:protoc-gen-grpc-java:${grpcVersion}"
        }
    }
    generateProtoTasks {
        ofSourceSet("main").forEach {
            it.plugins {
                id("grpc")
            }
        }
    }
}

dependencyManagement {
    imports {
        mavenBom("org.springframework.cloud:spring-cloud-dependencies:${property("springCloudVersion")}")
    }
}

tasks.withType<KotlinCompile> {
    kotlinOptions {
        freeCompilerArgs = listOf("-Xjsr305=strict")
        jvmTarget = "11"
    }
}

tasks.withType<Test> {
    useJUnitPlatform()
}