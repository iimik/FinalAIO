package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler;
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.git.GitHelper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 默认的Issue（浏览器）打开器
 *
 * @author iimik
 * @since 0.0.1
 * @see JiraIssueOpener
 **/
@Component
public class GitIssueOpener implements ElementHandler {
    /**
     * 浏览器打开工具
     */
    @Resource
    private BrowserOpener browserOpener;
    @Resource
    private DocHelper docHelper;
    @Resource
    private GitHelper gitHelper;

    @Override
    public void handle(PsiElement element) {

        if (!(element instanceof PsiDocTag)) {
            return;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();

        if (!IssueType.issue.name().equalsIgnoreCase(tagName)) {
            return;
        }

        final String value = docHelper.getDocTagValue(docTag);
        final String issuesUrl = gitHelper.getIssuesUrl(element, value);

        browserOpener.open(issuesUrl);


    }
}
