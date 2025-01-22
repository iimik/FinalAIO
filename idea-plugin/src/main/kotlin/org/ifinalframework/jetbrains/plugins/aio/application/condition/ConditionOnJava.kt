package org.ifinalframework.jetbrains.plugins.aio.application.condition


/**
 * ConditionOnJava
 *
 * @author iimik
 * @since 0.0.1
 **/
@ConditionOnLanguage("Java")
@Target(AnnotationTarget.CLASS)
@Retention(AnnotationRetention.RUNTIME)
annotation class ConditionOnJava()
