package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ElementApplication
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
public @interface ElementApplication {
    @AliasFor("components")
    Class<?>[] value() default {};

    @AliasFor("value")
    Class<?>[] components() default {};
}
