package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementApplication;
import org.ifinalframework.jetbrains.plugins.aio.browser.DefaultBrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.git.DefaultGitHelper;
import org.ifinalframework.jetbrains.plugins.aio.idea.aop.IdeaThreadAdvisor;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;

/**
 * IssueLineMarkerApplication
 *
 * @author iimik
 * @issue 3
 * @since 0.0.1
 **/
public class IssueLineMarkerApplication implements ElementApplication {

    @Override
    public void init(AnnotationConfigRegistry registry) {
        registry.register(IdeaThreadAdvisor.class);
        registry.register(IssueProperties.class);
        registry.register(GitIssueOpener.class);
        registry.register(JiraIssueOpener.class);
        registry.register(DefaultBrowserOpener.class);
        registry.register(DefaultDocHelper.class);
        registry.register(DefaultGitHelper.class);
    }

    @Override
    public void onEvent(ApplicationContext context, PsiElement element) {
        context.getBeanProvider(IssueOpener.class).forEach(issueOpener -> {
            if (issueOpener.isSupported(element)) {
                issueOpener.open(element);
            }
        });
    }
}
