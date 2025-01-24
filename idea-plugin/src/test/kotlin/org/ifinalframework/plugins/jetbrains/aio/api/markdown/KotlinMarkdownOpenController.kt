package org.ifinalframework.plugins.jetbrains.aio.api.markdown;

import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController


/**
 * Markdown
 *
 * @author iimik
 * @since 1.6.0
 **/
@RequestMapping("/kotlin")
@RestController
class KotlinMarkdownOpenController {

    /**
     * 注释
     */
    @GetMapping
    fun
            markdown(){

    }

    @GetMapping("/methodName")
    fun methodName(){

    }
}