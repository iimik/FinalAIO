package org.ifinalframework.api.idea.action;

import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.itangcent.idea.plugin.actions.BasicAnAction
import com.itangcent.intellij.context.ActionContext
import org.ifinalframework.api.idea.markdown.MarkdownOpener


/**
 * OpenMarkdownAction
 *
 * @author iimik
 * @since 2025-01-08 20:42
 **/
class OpenMarkdownAction : BasicAnAction("Open Markdown") {
    override fun actionPerformed(actionContext: ActionContext, project: Project?, anActionEvent: AnActionEvent) {
        super.actionPerformed(actionContext, project, anActionEvent)
        actionContext.runInReadUI {
            val element = anActionEvent.getData(CommonDataKeys.PSI_ELEMENT) as PsiElement
            actionContext.instance(MarkdownOpener::class).open(element)
        }
    }
}