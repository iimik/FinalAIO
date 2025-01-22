package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Api
 * <pre class="code">
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
 * </pre>
 *
 * @author iimik
 * @since 0.0.1
 **/
@Getter
@Setter
public class Api {
    @JsonProperty("_id")
    private String id;
    private String title;
    private String method;
    private String path;

    @Override
    public String toString() {
        return String.join(" ", id, title, method, path);
    }
}
