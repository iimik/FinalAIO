package org.ifinalframework.jetbrains.plugins.aio.issue;


import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

import lombok.Getter;
import lombok.Setter;

/**
 * IssueProperties
 *
 * @author iimik
 * @since 1.6.0
 **/
@Setter
@Getter
@Configuration
@ConfigurationProperties
public class IssueProperties {

    private Map<String, String> issues;

}
