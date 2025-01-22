package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * Project
 *
 * @author iimik
 * @since 0.0.1
 **/
@Getter
@Setter
public class Project {
    @JsonProperty("_id")
    private Long id;
    private String name;
}
