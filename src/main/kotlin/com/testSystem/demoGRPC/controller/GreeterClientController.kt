package com.testSystem.demoGRPC.controller

import com.testSystem.demoGRPC.greeter.GreeterGrpcKt
import com.testSystem.demoGRPC.greeter.HelloRequest
import io.grpc.ManagedChannelBuilder
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController

@RestController
class GreeterClientController {
    @GetMapping("/greeter/hello/{name}")
    fun hello(@PathVariable name: String): String = runBlocking {
        val channel = ManagedChannelBuilder.forAddress("localhost", 6565).usePlaintext().build()

        val request = HelloRequest.newBuilder().setName(name).build()
        val stub = GreeterGrpcKt.GreeterCoroutineStub(channel)

        val response = async { stub.hello(request) }
        response.await().text
    }
}