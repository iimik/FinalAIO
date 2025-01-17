package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.env.PropertySource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.SpringFactoriesLoader;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.browser.DefaultBrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.config.DefaultConfigHelper;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.swing.Icon;

import java.io.IOException;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Issue行标记提供者
 *
 * <h4>支持文档注释标签</h4>
 * <ul>
 *     <li>{@code @jira} Jira 项目管理</li>
 *     <li>{@code @issue} Git issue 管理</li>
 * </ul>
 *
 * @issue 3
 * @author iimik
 * @since 0.0.1
 **/
@Configuration
@EnableConfigurationProperties(IssueProperties.class)
public class IssueLineMarkerProvider implements LineMarkerProvider {

    private static final Logger logger = LoggerFactory.getLogger(IssueLineMarkerProvider.class);

    private static final Map<String, Icon> ICONS = new LinkedHashMap<>();

    static {
        ICONS.put("jira", IssueIcons.JIRA);
        ICONS.put("issue", IssueIcons.GIT);
    }

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement psiElement) {

        if (psiElement instanceof PsiDocTag) {
            final PsiDocTag tag = (PsiDocTag) psiElement;

            final Icon icon = ICONS.get(tag.getName());

            if (Objects.isNull(icon)) {
                return null;
            }

            final NavigationGutterIconBuilder builder = NavigationGutterIconBuilder.create(icon);
            builder.setTargets(psiElement);
            builder.setTooltipText("打开Jira");
            return builder.createLineMarkerInfo(psiElement, ((mouseEvent, element) -> {
                final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
                    @Override
                    public void onApplicationEvent(ApplicationEvent event) {
                        logger.info(event.toString());
                    }
                });
                final ConfigurableEnvironment environment = context.getEnvironment();
                final MutablePropertySources propertySources = environment.getPropertySources();

                final List<String> configPaths = new DefaultConfigHelper().getConfigPaths(element);
                Collections.reverse(configPaths);

                final List<PropertySourceLoader> propertySourceLoaders = SpringFactoriesLoader.loadFactories(PropertySourceLoader.class, getClass().getClassLoader());

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


                context.register(IssueProperties.class);
                context.register(IssueLineMarkerProvider.class);
                context.register(DefaultIssueOpener.class);
                context.register(DefaultBrowserOpener.class);
                context.register(DefaultDocHelper.class);

                context.refresh();

                final IssueOpener issueOpener = context.getBean(IssueOpener.class);
                issueOpener.open(psiElement);

            }));

        }


        return null;
    }

}
