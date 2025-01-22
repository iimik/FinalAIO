package org.ifinalframework.jetbrains.plugins.aio.api.yapi;


import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * YapiProperties
 *
 * @author iimik
 * @since 0.0.1
 **/
@Setter
@Getter
@ConfigurationProperties(prefix = "final.api.yapi")
public class YapiProperties {
    private String serverUrl;
    private String token;
}
