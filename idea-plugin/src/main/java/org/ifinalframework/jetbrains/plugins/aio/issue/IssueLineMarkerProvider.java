package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;
import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.browser.DefaultBrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.config.DefaultConfigHelper;
import org.ifinalframework.jetbrains.plugins.aio.git.DefaultGitHelper;
import org.jetbrains.annotations.NotNull;
import org.springframework.boot.env.PropertySourceLoader;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
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
 * @author iimik
 * @issue 3
 * @jira 3
 * @since 0.0.1
 **/
@Slf4j
public class IssueLineMarkerProvider implements LineMarkerProvider {

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement psiElement) {
        if (psiElement instanceof PsiDocTag) {
            final PsiDocTag tag = (PsiDocTag) psiElement;

            final IssueType issueType = IssueType.of(tag.getName());

            if (Objects.isNull(issueType)) {
                return null;
            }

            final NavigationGutterIconBuilder builder = NavigationGutterIconBuilder.create(issueType.getIcon());
            builder.setTargets(psiElement);
            builder.setTooltipText("打开Jira");
            return builder.createLineMarkerInfo(psiElement, ((mouseEvent, element) -> {
                $.async(() -> {
                    final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                    context.addApplicationListener(new ApplicationListener<ApplicationEvent>() {
                        @Override
                        public void onApplicationEvent(ApplicationEvent event) {
                            logger.info(event.toString());
                        }
                    });
                    final ConfigurableEnvironment environment = context.getEnvironment();
                    final MutablePropertySources propertySources = environment.getPropertySources();

                    final List<String> configPaths = $.computeInReadAction(() -> new DefaultConfigHelper().getConfigPaths(element));
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
                    context.register(GitIssueOpener.class);
                    context.register(JiraIssueOpener.class);
                    context.register(DefaultBrowserOpener.class);
                    context.register(DefaultDocHelper.class);
                    context.register(DefaultGitHelper.class);

                    context.refresh();

                    context.getBeanProvider(IssueOpener.class)
                            .forEach(issueOpener -> {
                                if (issueOpener.isSupported(psiElement)) {
                                    issueOpener.open(psiElement);
                                }
                            });
                });

            }));

        }


        return null;
    }

}
