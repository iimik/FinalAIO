package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Event Dispatch Thread (EDT)
 *
 * @author iimik
 * @since 0.0.1
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface EDT {
}
