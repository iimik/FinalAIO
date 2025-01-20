package org.ifinalframework.jetbrains.plugins.aio.application.aop;


import com.intellij.openapi.application.ApplicationManager;
import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

/**
 * ReadActionMethodInterceptor
 *
 * @author iimik
 * @see org.ifinalframework.jetbrains.plugins.aio.application.annotation.Async
 * @since 0.0.1
 **/
public class AsyncMethodInterceptor implements MethodInterceptor {
    @Override
    public Object invoke(MethodInvocation invocation) throws Throwable {

        ApplicationManager.getApplication().executeOnPooledThread(() -> {
            try {
                invocation.proceed();
            } catch (Throwable e) {
                throw new RuntimeException(e);
            }
        });
        return null;
    }
}
