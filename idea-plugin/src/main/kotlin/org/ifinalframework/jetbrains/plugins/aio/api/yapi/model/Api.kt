package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model

import com.fasterxml.jackson.annotation.JsonProperty


/**
 * Api
 *
 * ```json
 * {
 *     "edit_uid": 0,
 *     "status": "done",
 *     "api_opened": false,
 *     "tag": [],
 *     "_id": 50734,
 *     "method": "GET",
 *     "catid": 9273,
 *     "title": "AAA",
 *     "path": "/api/",
 *     "project_id": 96,
 *     "uid": 1142,
 *     "add_time": 1674110433
 * }
 * ```
 *
 * @author iimik
 * @since 1.6.0
 **/
data class Api(
    @JsonProperty("_id")
    val id: String,
    val title: String,
    val method: String,
    val path: String,
)
