package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.`$`
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.ifinalframework.plugins.jetbrains.aio.util.SpiUtil
import java.awt.event.MouseEvent


/**
 * Issue 行标记
 *
 * 对特定的文档标签添加Issue行标记，通过点击可以快速在浏览器中打开对应的URL。
 *
 * **支持的文档标签如下：**
 *
 * * `@jira`：Jira 项目管理
 * * `@issue`： Git Issue 管理，如 github.com。
 *
 * @issue 3
 * @author iimik
 * @since 0.0.1
 **/
class IssueLineMarkerProvider : LineMarkerProvider {

    private val issueService = DefaultIssueService(SpiUtil.languageSpi(DocService::class))

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        issueService.getIssue(element)?.let { issue ->
            return buildIssueLineMarkerInfo(issue, element)
        }
        return null;

    }


    private fun buildIssueLineMarkerInfo(issue: Issue, element: PsiElement): RelatedItemLineMarkerInfo<PsiElement> {
        val builder = NavigationGutterIconBuilder.create(issue.type.icon)
        builder.setTargets(element)
        builder.setTooltipText("Open issue in Browser!")
        return builder.createLineMarkerInfo(
            element
        ) { mouseEvent: MouseEvent?, element: PsiElement? ->
            `$`.run(
                IssueLineMarkerApplication::class.java, element
            )
        }
    }

}