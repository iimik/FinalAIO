package org.ifinalframework.plugins.jetbrains.aio.api.markdown

import org.springframework.boot.context.properties.ConfigurationProperties


/**
 * MarkdownProperties
 *
 * @author iimik
 * @since 1.6.0
 **/
@ConfigurationProperties("final.api.markdown")
data class MarkdownProperties(
    /**
     * Markdown根目录
     */
    val basePath: String = "docs/api",
)
