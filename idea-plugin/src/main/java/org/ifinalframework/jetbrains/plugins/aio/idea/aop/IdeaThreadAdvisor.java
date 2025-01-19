package org.ifinalframework.jetbrains.plugins.aio.idea.aop;


import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ThreadAnnotation;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

/**
 * IdeaThreadAdvisor
 *
 * @author iimik
 * @since 1.6.0
 **/
@Component
@EnableAspectJAutoProxy
public class IdeaThreadAdvisor extends DefaultPointcutAdvisor {
    public IdeaThreadAdvisor() {
        super(new AnnotationMatchingPointcut(null, ThreadAnnotation.class, true), new IdeaThreadModelMethodInterceptor());
    }
}
