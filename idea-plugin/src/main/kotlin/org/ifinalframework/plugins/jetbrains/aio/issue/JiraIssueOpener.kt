package org.ifinalframework.plugins.jetbrains.aio.issue;

import org.apache.commons.lang3.StringUtils
import org.ifinalframework.plugins.jetbrains.aio.browser.BrowserOpener
import org.ifinalframework.plugins.jetbrains.aio.jira.JiraProperties
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * JiraIssueOpener
 * @issue 3
 * @author iimik
 * @since 0.0.1
 * @see GitIssueOpener
 **/
@Component
@EnableConfigurationProperties(JiraProperties::class)
class JiraIssueOpener : IssueOpener {
    @Resource
    private lateinit var properties: JiraProperties

    /**
     * 浏览器打开工具
     */
    @Resource
    private lateinit var browserOpener: BrowserOpener

    override fun supported(issueType: IssueType): Boolean {
        return IssueType.JIRA == issueType
    }

    override fun open(issue: Issue) {
        if (StringUtils.isAnyBlank(properties!!.serverUrl, properties!!.serverUrl)) {
            return
        }

        val issuesUrl = properties!!.serverUrl + "/browse/" + properties!!.projectCode + "-" + issue.code
        browserOpener!!.open(issuesUrl)
    }
}