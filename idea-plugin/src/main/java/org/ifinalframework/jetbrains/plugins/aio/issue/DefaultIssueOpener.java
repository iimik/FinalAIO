package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;

import org.springframework.stereotype.Component;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.browser.BrowserOpener;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;

import javax.annotation.Resource;

import java.util.Map;

/**
 * 默认的Issue（浏览器）打开器
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
public class DefaultIssueOpener implements IssueOpener {
    /**
     * Issue配置属性
     */
    @Resource
    private IssueProperties issueProperties;
    /**
     * 浏览器打开工具
     */
    @Resource
    private BrowserOpener browserOpener;
    @Resource
    private DocHelper docHelper;

    @Override
    public void open(PsiElement element) {

        if (!(element instanceof PsiDocTag)) {
            return;
        }

        PsiDocTag docTag = (PsiDocTag) element;
        final String tagName = docTag.getName();
        final String value = docHelper.getDocTagValue(docTag);

        final Map<String, String> urlFormats = issueProperties.getIssues();
        if (MapUtils.isEmpty(urlFormats)) {
            return;
        }

        final String urlFormat = urlFormats.get(tagName);
        if (StringUtils.isEmpty(urlFormat)) {
            return;
        }

        final String url = urlFormat.formatted(value);

        browserOpener.open(url);


    }
}
