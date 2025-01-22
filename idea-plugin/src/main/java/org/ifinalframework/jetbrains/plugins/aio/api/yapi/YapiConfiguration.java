package org.ifinalframework.jetbrains.plugins.aio.api.yapi;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;

/**
 * YapiConfiguration
 *
 * @author iimik
 * @since 0.0.1
 **/
@Configuration
@EnableConfigurationProperties(YapiProperties.class)
@EnableFeignClients(clients = YapiClient.class)
public class YapiConfiguration {
    @Resource
    private YapiProperties properties;

}
