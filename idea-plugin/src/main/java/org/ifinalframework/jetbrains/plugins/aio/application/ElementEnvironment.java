package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.psi.PsiElement;
import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.ElementPropertySourcesLoader;
import org.ifinalframework.jetbrains.plugins.aio.config.DefaultConfigHelper;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.env.StandardEnvironment;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.SpringFactoriesLoader;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

/**
 * ElementEnvironment
 *
 * @author iimik
 * @issue 6
 * @since 0.0.1
 **/
@Slf4j
public class ElementEnvironment extends StandardEnvironment implements ElementPropertySourcesLoader {

    private static final String CONFIG_FILE_NAME = ".final.";

    @Override
    public void load(ClassLoader classLoader, PsiElement element) {
        final MutablePropertySources propertySources = getPropertySources();

        final String basePath = $.read.compute(() -> element.getProject().getBasePath());
        // load .final config file
        final List<String> configPaths = $.read.compute(() -> new DefaultConfigHelper().getConfigPaths(element));
        Collections.sort(configPaths);

        final List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, classLoader);

        for (String configPath : configPaths) {

            for (PropertySourceLoader propertySourceLoader : propertySourceLoaders) {
                for (String extension : propertySourceLoader.getFileExtensions()) {
                    try {
                        final String file = configPath + "/" + CONFIG_FILE_NAME + extension;
                        final Resource urlResource = new FileSystemResource(file);
                        if (urlResource.exists()) {
                            logger.info("found config file: {}", file.substring(basePath.length() + 1));
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


    }
}
