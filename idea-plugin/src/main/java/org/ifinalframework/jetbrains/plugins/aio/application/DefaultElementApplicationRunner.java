package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.config.DefaultConfigHelper;
import org.ifinalframework.jetbrains.plugins.aio.idea.aop.IdeaThreadAdvisor;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * DefaultElementApplicationRunner
 *
 * @author iimik
 * @since 0.0.1
 **/
public class DefaultElementApplicationRunner implements ElementApplicationRunner {
    @Override
    public void run(ElementApplication application, PsiElement element) {
        $.async(() -> {
            final ClassLoader classLoader = AopConfigException.class.getClassLoader();
            final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.setClassLoader(classLoader);
            final ConfigurableEnvironment environment = context.getEnvironment();
            final MutablePropertySources propertySources = environment.getPropertySources();

            final List<String> configPaths = $.read.compute(() -> new DefaultConfigHelper().getConfigPaths(element));
            Collections.reverse(configPaths);

            final List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, classLoader);

            for (String configPath : configPaths) {

                for (PropertySourceLoader propertySourceLoader : propertySourceLoaders) {
                    for (String extension : propertySourceLoader.getFileExtensions()) {
                        try {
                            final String file = configPath + "/.final." + extension;
                            final Resource urlResource = new FileSystemResource(file);
                            if (urlResource.exists()) {
                                final List<PropertySource<?>> sources = propertySourceLoader.load(file, urlResource);
                                for (PropertySource<?> source : sources) {
                                    propertySources.addFirst(source);
                                }
                            }
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

            }
            context.register(IdeaThreadAdvisor.class);
            application.init(context);
            context.refresh();
            application.onEvent(context, element);
        });
    }
}
