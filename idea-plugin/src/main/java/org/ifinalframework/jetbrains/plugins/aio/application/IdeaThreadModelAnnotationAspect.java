package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.Async;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadActionCompute;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadActionRun;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * IdeaThreadModelAnnotationAspect
 *
 * @author iimik
 * @since 0.0.1
 **/
@Aspect
@Component
@EnableAspectJAutoProxy
public class IdeaThreadModelAnnotationAspect {

    @Around("@annotation(ann)")
    public void readActionRun(ReadActionRun ann, ProceedingJoinPoint joinPoint) throws Throwable {
        ReadAction.run(() -> joinPoint.proceed());
    }

    @Around("@annotation(ann)")
    public Object readActionCompute(ReadActionCompute ann, ProceedingJoinPoint joinPoint) throws Throwable {
        return ReadAction.compute(() -> joinPoint.proceed());
    }

    @Around("@annotation(ann)")
    public void async(Async ann, ProceedingJoinPoint joinPoint) throws Throwable {
        ApplicationManager.getApplication().executeOnPooledThread(
                () -> {
                    try {
                        joinPoint.proceed();
                    } catch (Throwable e) {
                        throw new RuntimeException(e);
                    }
                }
        );
    }

}
