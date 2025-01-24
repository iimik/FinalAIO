package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.application.ElementHandler
import org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener
import org.ifinalframework.plugins.jetbrains.aio.git.GitHelper
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
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
class GitIssueHandler : org.ifinalframework.plugins.jetbrains.aio.application.ElementHandler {
    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener

    @Resource
    private lateinit var docService: DocService


    @Resource
    private lateinit var gitHelper: org.ifinalframework.plugins.jetbrains.aio.git.GitHelper

    override fun handle(element: PsiElement) {
        val tagName = docService!!.getTagName(element) ?: return
        IssueType.ofNullable(tagName) ?: return
        val tagValue = docService!!.getTagValue(element) ?: return

        val issuesUrl = gitHelper!!.getIssuesUrl(element, tagValue)

        browserOpener!!.open(issuesUrl)
    }
}