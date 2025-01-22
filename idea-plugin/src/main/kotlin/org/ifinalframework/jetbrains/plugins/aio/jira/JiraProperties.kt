package org.ifinalframework.jetbrains.plugins.aio.jira

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * JiraProperties
 *
 * @author iimik
 * @since 0.0.1
 **/
@ConfigurationProperties(prefix = "final.jira")
data class JiraProperties(
    /**
     * 服务地址
     */
    val serverUrl: String?,
    /**
     * 项目编码
     */
    val projectCode: String?
)
