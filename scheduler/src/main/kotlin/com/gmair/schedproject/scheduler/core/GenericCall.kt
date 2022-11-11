package com.gmair.schedproject.scheduler.core

import com.fasterxml.jackson.annotation.JsonProperty

data class GenericCall(
    @JsonProperty("inter") val inter: String,
    @JsonProperty("methodName") val methodName: String,
    @JsonProperty("paramName") val paramName: String,
    @JsonProperty("GenericCallParams") val genericCallParams: String
)
