package org.ifinalframework.jetbrains.plugins.aio.api.yapi;

import jakarta.annotation.Resource
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.cloud.openfeign.EnableFeignClients
import org.springframework.context.annotation.Configuration


/**
 * YapiConfiguration
 *
 * @author iimik
 * @since 1.6.0
 **/
@Configuration
@EnableConfigurationProperties(YapiProperties::class)
@EnableFeignClients(clients = [YapiClient::class])
open class YapiConfiguration {
    @Resource
    private lateinit var yapiProperties: YapiProperties
}