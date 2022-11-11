package com.gmair.schedproject.scheduler.controller

import com.gmair.schedproject.scheduler.core.GenericCall
import com.gmair.schedproject.scheduler.service.GenericCallService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
    private val genericCallService: GenericCallService,
) {
    @PostMapping("/genericCall")
    fun test(@RequestBody genericCall: GenericCall): Any {
        return genericCallService.invoke(genericCall)
    }
}
