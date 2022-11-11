package com.gmair.schedproject.testprovider

import com.gmair.schedproject.GenericCallParams
import com.gmair.schedproject.GenericCallResp
import com.gmair.schedproject.GenericCallResult
import com.gmair.schedproject.TestInterface
import org.apache.dubbo.config.annotation.DubboService
import org.apache.dubbo.config.spring.context.annotation.EnableDubbo
import org.apache.dubbo.rpc.RpcContext
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
@EnableDubbo
class TestProviderApplication

@DubboService
class TestServiceImpl: TestInterface {
    override fun message(params: GenericCallParams): GenericCallResp {
        println("params: $params, call from ${RpcContext.getContext().remoteAddress}")
        return GenericCallResp(GenericCallResult.FINISHED)
    }
}

fun main(args: Array<String>) {
    runApplication<TestProviderApplication>(*args)
}
