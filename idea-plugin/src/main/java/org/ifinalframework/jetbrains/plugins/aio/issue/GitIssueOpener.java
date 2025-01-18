package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;

import org.springframework.stereotype.Component;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener;
import org.ifinalframework.jetbrains.plugins.aio.git.GitHelper;

import javax.annotation.Resource;

/**
 * 默认的Issue（浏览器）打开器
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
public class GitIssueOpener implements IssueOpener {
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
    public boolean isSupported(PsiElement element) {

        if (!(element instanceof PsiDocTag)) {
            return false;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();
        return IssueType.issue.name().equalsIgnoreCase(tagName);
    }

    @Override
    public void open(PsiElement element) {

        if (!(element instanceof PsiDocTag)) {
            return;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();
        final String value = docHelper.getDocTagValue(docTag);
        final String issuesUrl = gitHelper.getIssuesUrl(element, value);

        browserOpener.open(issuesUrl);


    }
}
