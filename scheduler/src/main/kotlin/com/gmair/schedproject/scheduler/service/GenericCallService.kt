package com.gmair.schedproject.scheduler.service

import com.alibaba.fastjson.JSON
import com.fasterxml.jackson.databind.ObjectMapper
import com.gmair.schedproject.scheduler.core.GenericCall
import org.apache.dubbo.config.ApplicationConfig
import org.apache.dubbo.config.ReferenceConfig
import org.apache.dubbo.config.RegistryConfig
import org.apache.dubbo.rpc.service.GenericService
import org.springframework.stereotype.Service

@Service
class GenericCallService {
    private val applicationConfig = ApplicationConfig("scheduler")
    init {
        val registryConfig = RegistryConfig()
        registryConfig.address = "zookeeper://127.0.0.1:2181"
        applicationConfig.registry = registryConfig
    }
    private fun newConfig(inter: String): ReferenceConfig<GenericService> {
        val referenceConfig = ReferenceConfig<GenericService>()
        referenceConfig.setInterface(inter)
        referenceConfig.application = applicationConfig
        referenceConfig.generic = "true"
        return referenceConfig
    }
    private fun getConfig(inter: String): GenericService {
        return newConfig(inter).get()
    }
    fun invoke(params: GenericCall): Any {
        val genericService = getConfig(params.inter)
        val param = JSON.parseObject(params.genericCallParams, Class.forName(params.paramName))
        val map = genericService.`$invoke`(params.methodName, arrayOf(params.paramName), arrayOf<Any>(param)) as Map<*, *>
        return ObjectMapper().convertValue(map, Class.forName(map["class"] as String))
    }
}
