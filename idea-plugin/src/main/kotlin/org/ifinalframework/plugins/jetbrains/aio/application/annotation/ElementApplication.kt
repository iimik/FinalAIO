package org.ifinalframework.plugins.jetbrains.aio.application.annotation

import org.springframework.core.annotation.AliasFor
import org.springframework.stereotype.Component
import kotlin.reflect.KClass


/**
 * ElementApplication
 *
 * @author iimik
 * @since 1.6.0
 **/
@Component
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ElementApplication(
    @get:AliasFor("components") val value: Array<KClass<*>> = [],
    @get:AliasFor("value") val components: Array<KClass<*>> = []
)
