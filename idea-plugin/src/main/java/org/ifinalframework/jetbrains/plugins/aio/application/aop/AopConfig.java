package org.ifinalframework.jetbrains.plugins.aio.application.aop;


import org.aopalliance.intercept.MethodInterceptor;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.Async;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.EDT;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadAction;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.WriteAction;
import org.springframework.aop.Advisor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

import java.lang.annotation.Annotation;

/**
 * AopConfig
 *
 * @author iimik
 * @since 1.6.0
 **/
@Configuration
@EnableAspectJAutoProxy
public class AopConfig {
    @Bean
    public Advisor readActionAdvisor() {
        return buildAdvisor(ReadAction.class, new ReadActionMethodInterceptor());
    }

    @Bean
    public Advisor writeActionAdvisor() {
        return buildAdvisor(WriteAction.class, new WriteActionMethodInterceptor());
    }

    @Bean
    public Advisor asyncActionAdvisor() {
        return buildAdvisor(Async.class, new AsyncMethodInterceptor());
    }

    @Bean
    public Advisor edtAdvisor() {
        return buildAdvisor(EDT.class, new EDTMethodInterceptor());
    }

    private Advisor buildAdvisor(Class<? extends Annotation> annClass, MethodInterceptor methodInterceptor) {
        return new DefaultPointcutAdvisor(new AnnotationMatchingPointcut(null, annClass, true), methodInterceptor);
    }
}
