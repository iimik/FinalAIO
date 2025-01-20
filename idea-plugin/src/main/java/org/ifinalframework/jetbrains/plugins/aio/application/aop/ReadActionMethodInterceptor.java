package org.ifinalframework.jetbrains.plugins.aio.application.aop;


import com.intellij.openapi.application.ReadAction;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * ReadActionMethodInterceptor
 *
 * @author iimik
 * @see org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadAction
 * @since 0.0.1
 **/
public class ReadActionMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        final Method method = invocation.getMethod();
        final Class<?> returnType = method.getReturnType();

        if (void.class.equals(returnType)) {
            ReadAction.run(() -> invocation.proceed());
            return null;
        } else {
            return ReadAction.compute(() -> invocation.proceed());
        }

    }
}
