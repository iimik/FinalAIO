package org.ifinalframework.jetbrains.plugins.aio.issue;

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import org.ifinalframework.jetbrains.plugins.aio.`$`
import org.ifinalframework.jetbrains.plugins.aio.service.DocTagService
import org.ifinalframework.jetbrains.plugins.aio.util.SpiUtil
import java.awt.event.MouseEvent


/**
 * IssueLineMarkerProvider
 *
 * @issue 3
 * @author iimik
 * @since 0.0.1
 **/
class IssueLineMarkerProvider : LineMarkerProvider {

    private val docTagService = SpiUtil.languageSpi(DocTagService::class)

    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        val tagName = docTagService.getTagName(element) ?: return null
        val issueType = IssueType.ofNullable(tagName) ?: return null
        val builder = NavigationGutterIconBuilder.create(issueType.icon)
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