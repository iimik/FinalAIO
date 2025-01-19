package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ThreadAnnotation
 *
 * @author iimik
 * @since 1.6.0
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.ANNOTATION_TYPE)
public @interface ThreadAnnotation {

    ThreadModel value();

    enum ThreadModel {
        READ_ACTION_RUN,
        READ_ACTION_COMPUTE,
        ASYNC
    }

}
