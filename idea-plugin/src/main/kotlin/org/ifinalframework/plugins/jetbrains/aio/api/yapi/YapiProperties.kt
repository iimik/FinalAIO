package org.ifinalframework.plugins.jetbrains.aio.api.yapi

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * YapiProperties
 *
 * @author iimik
 * @since 1.6.0
 **/
@ConfigurationProperties("final.api.yapi")
data class YapiProperties(
    val serverUrl: String?,
    val token: String?,
)
