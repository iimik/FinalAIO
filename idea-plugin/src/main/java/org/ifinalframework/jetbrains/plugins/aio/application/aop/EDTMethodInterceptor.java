package org.ifinalframework.jetbrains.plugins.aio.application.aop;


import com.intellij.openapi.application.ApplicationManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * ReadActionMethodInterceptor
 *
 * @author iimik
 * @see org.ifinalframework.jetbrains.plugins.aio.application.annotation.EDT
 * @since 0.0.1
 **/
public class EDTMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {
        ApplicationManager.getApplication().invokeLater(() -> {
            try {
                invocation.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }
}
