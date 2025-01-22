package org.ifinalframework.jetbrains.plugins.aio.issue;

import com.intellij.psi.PsiElement
import org.apache.commons.lang3.StringUtils
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener
import org.ifinalframework.jetbrains.plugins.aio.jira.JiraProperties
import org.ifinalframework.jetbrains.plugins.aio.service.DocTagService
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
class JiraIssueHandler : ElementHandler {
    @Resource
    private lateinit var properties: JiraProperties

    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: BrowserOpener

    @Resource
    private lateinit var docTagService: DocTagService

    override fun handle(element: PsiElement) {

        if (StringUtils.isAnyBlank(properties!!.serverUrl, properties!!.serverUrl)) {
            return
        }

        val tagName = docTagService.getTagName(element)
        IssueType.ofNullable(tagName) ?: return

        val value = docTagService!!.getTagValue(element)
        val issuesUrl = properties!!.serverUrl + "/browse/" + properties!!.projectCode + "-" + value
        browserOpener!!.open(issuesUrl)
    }
}