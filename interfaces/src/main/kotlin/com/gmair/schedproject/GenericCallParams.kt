package com.gmair.schedproject
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonProperty


data class GenericCallParams(
    @JsonProperty("id") val id: Int
):java.io.Serializable

enum class GenericCallResult {
    DEFAULT,
    FINISHED,
    SKIPPED,
    ABORTED,
}
@JsonIgnoreProperties(ignoreUnknown = true)
data class GenericCallResp(
    var result : GenericCallResult = GenericCallResult.DEFAULT,
    var errMsg: String = "",
) {

    fun isComplete(): Boolean {
        return result == GenericCallResult.FINISHED ||
            result == GenericCallResult.SKIPPED ||
            result == GenericCallResult.ABORTED
    }
}
