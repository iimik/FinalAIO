package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import com.intellij.util.ThrowableRunnable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ReadActionRun
 *
 * @author iimik
 * @see com.intellij.openapi.application.ReadAction#run(ThrowableRunnable)
 * @since 0.0.1
 **/
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@ThreadAnnotation(ThreadAnnotation.ThreadModel.READ_ACTION_RUN)
public @interface ReadActionRun {
}
