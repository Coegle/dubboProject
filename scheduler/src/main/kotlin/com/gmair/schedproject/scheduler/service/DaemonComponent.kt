package com.gmair.schedproject.scheduler.service

import com.gmair.schedproject.scheduler.core.GenericCall
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.springframework.beans.factory.DisposableBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Component
import javax.annotation.PostConstruct

@Component
final class DaemonComponent: DisposableBean {
    @Autowired
    private lateinit var genericCallService: GenericCallService
    @PostConstruct
    fun run() {
        CoroutineScope(Dispatchers.Default).launch {
            genericCallService.invoke(GenericCall("com.gmair.schedproject.TestInterface",
                "message",
                "com.gmair.schedproject.GenericCallParams", "{\"id\": 5}"))
        }
    }

    override fun destroy() {
        println("destroy")
    }
}
