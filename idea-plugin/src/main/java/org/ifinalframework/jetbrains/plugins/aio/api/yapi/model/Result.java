package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Result
 *
 * @author iimik
 * @since 0.0.1
 **/
@Getter
@Setter
public class Result<T> {
    @JsonProperty("errcode")
    private String errCode;
    @JsonProperty("errmsg")
    private String errMsg;
    private T data;

    public boolean isSuccess() {
        return "0".equals(errCode);
    }
}
