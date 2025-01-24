package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import jakarta.annotation.Resource
import org.ifinalframework.plugins.jetbrains.aio.application.ElementHandler
import org.ifinalframework.plugins.jetbrains.aio.application.annotation.ElementApplication
import org.ifinalframework.plugins.jetbrains.aio.browser.DefaultBrowserOpener
import org.ifinalframework.plugins.jetbrains.aio.git.DefaultGitHelper
import org.ifinalframework.plugins.jetbrains.aio.service.DocService

/**
 * Issue 行标记
 *
 * ## 文档注释
 *
 * ```
 * /**
 * * @jira 3 文档标记
 * * @issue 3 文档标记
 * */
 * ```
 *
 * ## 行注释
 *
 * ```
 * // #3 行标记
 * // @issue 3 行标记
 * // @jira 3 行标记
 * ```
 *
 * @author iimik
 * @issue 3 文档标记
 * @issue 11 行标记
 * @see IssueLineMarkerProvider
 * @see GitIssueOpener
 * @see JiraIssueOpener
 * @since 0.0.1
 **/
@ElementApplication(
    [
        GitIssueOpener::class,
        JiraIssueOpener::class,
        DefaultIssueService::class,
        DefaultBrowserOpener::class,
        DefaultGitHelper::class,
        DocService::class
    ]
)
class IssueLineMarkerApplication : ElementHandler {

    @Resource
    private lateinit var issueService: IssueService

    @Resource
    private lateinit var issueOpeners: List<IssueOpener>

    override fun handle(element: PsiElement) {
        issueService.getIssue(element)?.let { issue ->
            issueOpeners.filter { it.supported(issue.type) }
                .forEach { it.open(issue) }
        }
    }
}