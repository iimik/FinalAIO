package org.ifinalframework.jetbrains.plugins.aio.issue;

import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication
import org.ifinalframework.jetbrains.plugins.aio.browser.DefaultBrowserOpener
import org.ifinalframework.jetbrains.plugins.aio.git.DefaultGitHelper
import org.ifinalframework.jetbrains.plugins.aio.service.DocService

/**
 * IssueLineMarker 应用。
 * 在{@code @issue 或 @jira} LineMarker 点击时，在浏览器中打开对应的URL。
 *
 * @author iimik
 * @issue 3
 * @see IssueLineMarkerProvider
 * @see GitIssueHandler
 * @see JiraIssueHandler
 * @since 0.0.1
 **/
@ElementApplication(
    [
        GitIssueHandler::class,
        JiraIssueHandler::class,
        DefaultBrowserOpener::class,
        DefaultGitHelper::class,
        DocService::class
    ]
)
class IssueLineMarkerApplication {
}