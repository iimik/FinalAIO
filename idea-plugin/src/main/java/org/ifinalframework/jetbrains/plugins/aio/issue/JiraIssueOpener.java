package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Component;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.jira.JiraProperties;

import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

/**
 * JiraIssueOpener
 *
 * @author iimik
 * @since 0.0.1
 * @see GitIssueOpener
 **/
@Component
@EnableConfigurationProperties(JiraProperties.class)
public class JiraIssueOpener implements IssueOpener {

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
    public boolean isSupported(PsiElement element) {
        if (!(element instanceof PsiDocTag)) {
            return false;
        }

        PsiDocTag docTag = (PsiDocTag) element;

        return IssueType.jira.name().equalsIgnoreCase(docTag.getName());

    }

    @Override
    public void open(PsiElement element) {
        if (!(element instanceof PsiDocTag)) {
            return;
        }

        if(StringUtils.isAnyBlank(properties.getServerUrl(), properties.getServerUrl())){
            return;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();
        final String value = docHelper.getDocTagValue(docTag);
        final String issuesUrl = properties.getServerUrl() + "/browse/" + properties.getProjectCode() + "-" + value;
        browserOpener.open(issuesUrl);
    }
}
