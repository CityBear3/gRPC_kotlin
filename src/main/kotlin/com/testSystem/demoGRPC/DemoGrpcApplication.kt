package com.testSystem.demoGRPC

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DemoGrpcApplication

fun main(args: Array<String>) {
	runApplication<DemoGrpcApplication>(*args)
}
