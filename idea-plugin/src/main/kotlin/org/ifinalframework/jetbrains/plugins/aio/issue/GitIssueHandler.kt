package org.ifinalframework.jetbrains.plugins.aio.issue;

import com.intellij.psi.PsiElement
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener
import org.ifinalframework.jetbrains.plugins.aio.git.GitHelper
import org.ifinalframework.jetbrains.plugins.aio.service.DocTagService
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * GitIssueOpener
 *
 * @author iimik
 * @since 0.0.1
 * @see JiraIssueHandler
 **/
@Component
class GitIssueHandler : ElementHandler {
    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: BrowserOpener

    @Resource
    private lateinit var docTagService: DocTagService


    @Resource
    private lateinit var gitHelper: GitHelper

    override fun handle(element: PsiElement) {
        val tagName = docTagService!!.getTagName(element) ?: return
        IssueType.ofNullable(tagName) ?: return
        val tagValue = docTagService!!.getTagValue(element) ?: return

        val issuesUrl = gitHelper!!.getIssuesUrl(element, tagValue)

        browserOpener!!.open(issuesUrl)
    }
}