package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementApplication;
import org.ifinalframework.jetbrains.plugins.aio.browser.DefaultBrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.git.DefaultGitHelper;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;

/**
 * IssueLineMarker 应用。
 * 在{@code @issue 或 @jira} LineMarker 点击时，在浏览器中打开对应的URL。
 *
 * @author iimik
 * @issue 3
 * @see IssueLineMarkerProvider
 * @see IssueOpener
 * @since 0.0.1
 **/
public class IssueLineMarkerApplication implements ElementApplication {

    @Override
    public void init(AnnotationConfigRegistry registry) {
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
