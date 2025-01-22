package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


/**
 * 分类菜单
 *
 * @author iimik
 * @since 0.0.1
 **/
@Getter
@Setter
public class CatMenu {
    @JsonProperty("_id")
    private Long id;
    private String name;

    @Override
    public String toString() {
        return name;
    }
}
