package com.testSystem.demoGRPC.service

import org.lognet.springboot.grpc.GRpcService
import com.testSystem.demoGRPC.greeter.GreeterGrpcKt
import com.testSystem.demoGRPC.greeter.HelloRequest
import com.testSystem.demoGRPC.greeter.HelloResponse

@GRpcService
class GreeterService : GreeterGrpcKt.GreeterCoroutineImplBase() {
    override suspend fun hello(request: HelloRequest): HelloResponse {
        return HelloResponse.newBuilder().setText("Hello ${request.name}").build()
    }
}