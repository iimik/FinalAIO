package org.ifinalframework.jetbrains.plugins.aio.application;


import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication;
import org.ifinalframework.jetbrains.plugins.aio.core.annotation.LanguageSpi;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.AbstractBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.AnnotatedBeanDefinitionReader;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.core.annotation.AnnotatedElementUtils;

import java.util.Objects;

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
                    final Class<?>[] components = annotation.value();
                    for (Class<?> component : components) {
                        final LanguageSpi languageSpi = component.getAnnotation(LanguageSpi.class);
                        if (Objects.isNull(languageSpi)) {
                            if (registry instanceof AnnotationConfigRegistry annotationConfigRegistry) {
                                annotationConfigRegistry.register(component);
                            } else {
                                reader.register(component);
                            }
                        } else {
                            final Class<?>[] classes = languageSpi.value();
                            if (registry instanceof AnnotationConfigRegistry annotationConfigRegistry) {
                                annotationConfigRegistry.register(classes);
                            } else {
                                reader.register(classes);
                            }
                        }

                    }


                }
            }
        }
    }
}
