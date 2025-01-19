package org.ifinalframework.jetbrains.plugins.aio.application.annotation;


import com.intellij.openapi.util.ThrowableComputable;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * ReadActionCompute
 *
 * @author iimik
 * @issue 4
 * @see com.intellij.openapi.application.ReadAction#compute(ThrowableComputable)
 * @since 0.0.1
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
@ThreadAnnotation(ThreadAnnotation.ThreadModel.READ_ACTION_COMPUTE)
public @interface ReadActionCompute {
}
