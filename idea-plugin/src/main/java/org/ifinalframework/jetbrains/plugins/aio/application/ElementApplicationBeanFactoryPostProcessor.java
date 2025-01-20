package org.ifinalframework.jetbrains.plugins.aio.application;


import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.core.annotation.AnnotatedElementUtils;

/**
 * ElementApplicationBeanFactoryPostProcessor
 *
 * @author iimik
 * @since 0.0.1
 **/
@Slf4j
public class ElementApplicationBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {


    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry registry) throws BeansException {

        final AnnotatedBeanDefinitionReader reader = new AnnotatedBeanDefinitionReader(registry);

        for (String beanDefinitionName : registry.getBeanDefinitionNames()) {
            final BeanDefinition beanDefinition = registry.getBeanDefinition(beanDefinitionName);
            if (beanDefinition instanceof AbstractBeanDefinition definition) {
                final Class<?> beanClass = definition.getBeanClass();
                final ElementApplication annotation = AnnotatedElementUtils.getMergedAnnotation(beanClass, ElementApplication.class);
                if (annotation != null) {
                    final Class<?>[] components = annotation.components();
                    if (components.length > 0) {
                        if (registry instanceof AnnotationConfigRegistry annotationConfigRegistry) {
                            annotationConfigRegistry.register(components);
                        } else {
                            reader.register(components);
                        }
                    }
                }
            }
        }
    }
}
