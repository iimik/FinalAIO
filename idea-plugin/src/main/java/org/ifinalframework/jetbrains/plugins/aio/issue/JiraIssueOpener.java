package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;
import org.apache.commons.lang3.StringUtils;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler;
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.jira.JiraProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * JiraIssueOpener
 *
 * @author iimik
 * @see GitIssueOpener
 * @since 0.0.1
 **/
@Component
@EnableConfigurationProperties(JiraProperties.class)
public class JiraIssueOpener implements ElementHandler {

    @Resource
    private JiraProperties properties;
    /**
     * 浏览器打开工具
     */
    @Resource
    private BrowserOpener browserOpener;
    @Resource
    private DocHelper docHelper;

    @Override
    public void handle(PsiElement element) {
        if (!(element instanceof PsiDocTag)) {
            return;
        }

        if (StringUtils.isAnyBlank(properties.getServerUrl(), properties.getServerUrl())) {
            return;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();

        if (!IssueType.jira.name().equalsIgnoreCase(tagName)) {
            return;
        }

        final String value = docHelper.getDocTagValue(docTag);
        final String issuesUrl = properties.getServerUrl() + "/browse/" + properties.getProjectCode() + "-" + value;
        browserOpener.open(issuesUrl);
    }
}
