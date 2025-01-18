package org.ifinalframework.jetbrains.plugins.aio.jira;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

/**
 * JiraProperties
 *
 * @author iimik
 * @since 0.0.1
 **/
@Setter
@Getter
@Configuration
@ConfigurationProperties(prefix = "jira")
public class JiraProperties {

    /**
     * 服务地址
     */
    private String serverUrl;
    /**
     * 项目编码
     */
    private String projectCode;
}
