package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Async
 *
 * @author iimik
 * @see com.intellij.openapi.application.Application#executeOnPooledThread(Runnable)
 * @since 1.6.0
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ThreadAnnotation(ThreadAnnotation.ThreadModel.ASYNC)
public @interface Async {
}
