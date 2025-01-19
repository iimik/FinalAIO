package org.ifinalframework.jetbrains.plugins.aio.idea.aop;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ThreadAnnotation;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.core.annotation.AnnotationUtils;

import java.lang.reflect.Method;
import java.util.Objects;

/**
 * IdeaThreadModelMethodInterceptor
 *
 * @author iimik
 * @since 0.0.1
 **/
public class IdeaThreadModelMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        final Method method = invocation.getMethod();
        final ThreadAnnotation annotation = AnnotationUtils.findAnnotation(method, ThreadAnnotation.class);

        if (Objects.isNull(annotation)) {
            return invocation.proceed();
        }

        final ThreadAnnotation.ThreadModel model = annotation.value();


        switch (model) {
            case ASYNC:
                ApplicationManager.getApplication().executeOnPooledThread(() -> {
                    try {
                        invocation.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                });
                break;
            case READ_ACTION_RUN:
                ReadAction.run(() -> {
                    invocation.proceed();
                });
                break;
            case READ_ACTION_COMPUTE:
                return ReadAction.compute(() -> invocation.proceed());
        }

        throw new AopConfigException("Unsupported thread model: " + model);
    }
}
