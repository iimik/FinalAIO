package org.ifinalframework.plugins.jetbrains.aio.api.yapi.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Result
 *
 * @author iimik
 * @since 1.6.0
 **/
data class Result<T>(
    @JsonProperty("errcode")
    val errCode: String,
    @JsonProperty("errmsg")
    val errMsg: String?,
    val data: T
) {
    fun isSuccess(): Boolean = errCode == "0"
}
