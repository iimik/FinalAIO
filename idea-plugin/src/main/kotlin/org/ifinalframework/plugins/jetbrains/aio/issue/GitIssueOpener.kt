package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * GitIssueOpener
 *
 * @author iimik
 * @since 0.0.1
 * @see JiraIssueOpener
 **/
@Component
class GitIssueOpener : IssueOpener {

    @Resource
    private lateinit var element: PsiElement

    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener

    @Resource
    private lateinit var docService: DocService

    override fun supported(issueType: IssueType): Boolean {
        return IssueType.ISSUE == issueType
    }

    @Resource
    private lateinit var gitHelper: org.ifinalframework.plugins.jetbrains.aio.git.GitHelper


    override fun open(issue: Issue) {
        val issuesUrl = gitHelper.getIssuesUrl(element, issue.code)
        browserOpener!!.open(issuesUrl)
    }

}