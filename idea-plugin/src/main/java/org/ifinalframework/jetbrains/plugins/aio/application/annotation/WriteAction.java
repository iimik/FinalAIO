package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * WriteAction
 *
 * @author iimik
 * @see com.intellij.openapi.application.WriteAction
 * @since 0.0.1
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface WriteAction {
}
