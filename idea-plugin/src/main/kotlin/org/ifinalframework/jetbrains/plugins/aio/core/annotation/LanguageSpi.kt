package org.ifinalframework.jetbrains.plugins.aio.core.annotation

import kotlin.reflect.KClass


/**
 * LanguageSpi
 *
 * @author iimik
 * @since 0.0.1
 **/
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class LanguageSpi(
    val value: Array<KClass<*>> = [],
)
