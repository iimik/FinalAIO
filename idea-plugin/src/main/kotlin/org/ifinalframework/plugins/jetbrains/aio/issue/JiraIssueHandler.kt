package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import org.apache.commons.lang3.StringUtils
import org.ifinalframework.plugins.jetbrains.aio.application.ElementHandler
import org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener
import org.ifinalframework.plugins.jetbrains.aio.jira.JiraProperties
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * JiraIssueOpener
 * @issue 3
 * @author iimik
 * @since 0.0.1
 * @see GitIssueHandler
 **/
@Component
@EnableConfigurationProperties(JiraProperties::class)
class JiraIssueHandler : org.ifinalframework.plugins.jetbrains.aio.application.ElementHandler {
    @Resource
    private lateinit var properties: JiraProperties

    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener

    @Resource
    private lateinit var docService: DocService

    override fun handle(element: PsiElement) {

        if (StringUtils.isAnyBlank(properties!!.serverUrl, properties!!.serverUrl)) {
            return
        }

        val tagName = docService.getTagName(element)
        IssueType.ofNullable(tagName) ?: return

        val value = docService!!.getTagValue(element)
        val issuesUrl = properties!!.serverUrl + "/browse/" + properties!!.projectCode + "-" + value
        browserOpener!!.open(issuesUrl)
    }
}