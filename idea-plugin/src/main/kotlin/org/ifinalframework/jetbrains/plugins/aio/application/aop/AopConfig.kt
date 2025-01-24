package org.ifinalframework.jetbrains.plugins.aio.application.aop;

import org.aopalliance.intercept.MethodInterceptor
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.Async
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.EDT
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadAction
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.WriteAction
import org.springframework.aop.Advisor
import org.springframework.aop.support.DefaultPointcutAdvisor
import org.springframework.aop.support.annotation.AnnotationMatchingPointcut
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.EnableAspectJAutoProxy


/**
 * AopConfig
 *
 * @author iimik
 * @since 1.6.0
 **/
@Configuration
@EnableAspectJAutoProxy
open class AopConfig {
    @Bean
    open fun readActionAdvisor(): Advisor {
        return buildAdvisor(ReadAction::class.java, ReadActionMethodInterceptor())
    }

    @Bean
    open fun writeActionAdvisor(): Advisor {
        return buildAdvisor(WriteAction::class.java, WriteActionMethodInterceptor())
    }

    @Bean
    open fun asyncActionAdvisor(): Advisor {
        return buildAdvisor(Async::class.java, AsyncMethodInterceptor())
    }

    @Bean
    open fun edtAdvisor(): Advisor {
        return buildAdvisor(EDT::class.java, EDTMethodInterceptor())
    }

    private fun buildAdvisor(annClass: Class<out Annotation?>, methodInterceptor: MethodInterceptor): Advisor {
        return DefaultPointcutAdvisor(AnnotationMatchingPointcut(null, annClass, true), methodInterceptor)
    }
}