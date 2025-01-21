package org.ifinalframework.jetbrains.plugins.aio.application.aop;


import com.intellij.openapi.application.WriteAction;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import java.lang.reflect.Method;

/**
 * ReadActionMethodInterceptor
 *
 * @author iimik
 * @see org.ifinalframework.jetbrains.plugins.aio.application.annotation.WriteAction
 * @since 0.0.1
 **/
public class WriteActionMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        final Method method = invocation.getMethod();
        final Class<?> returnType = method.getReturnType();

        if (void.class.equals(returnType)) {
            WriteAction.run(() -> invocation.proceed());
            return null;
        } else {
            return WriteAction.compute(() -> invocation.proceed());
        }

    }
}
