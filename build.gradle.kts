import org.jetbrains.kotlin.gradle.tasks.KotlinCompile
import com.google.protobuf.gradle.generateProtoTasks
import com.google.protobuf.gradle.id
import com.google.protobuf.gradle.plugins
import com.google.protobuf.gradle.protobuf
import com.google.protobuf.gradle.protoc

plugins {
	id("org.springframework.boot") version "2.5.3"
	id("io.spring.dependency-management") version "1.0.11.RELEASE"
	id("com.google.protobuf") version "0.8.15"
	id("idea")
	kotlin("jvm") version "1.5.21"
	kotlin("plugin.spring") version "1.5.21"
}

group = "com.testSystem"
version = "0.0.1-SNAPSHOT"
java.sourceCompatibility = JavaVersion.VERSION_16

repositories {
	mavenCentral()
}

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-web")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.grpc:grpc-kotlin-stub:1.0.0")
	implementation("io.grpc:grpc-netty:1.35.0")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor:1.4.2")
	implementation("io.github.lognet:grpc-spring-boot-starter:4.5.5")
	testImplementation("org.springframework.boot:spring-boot-starter-test")
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "16"
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

protobuf {
	protoc {
		artifact = "com.google.protobuf:protoc:3.15.1"
	}

	plugins {
		id("grpc") {
			artifact = "io.grpc:protoc-gen-grpc-java:1.36.0"
		}

		id("grpckt") {
			artifact = "io.grpc:protoc-gen-grpc-kotlin:1.0.0:jdk7@jar"
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