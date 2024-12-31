package com.itangcent.idea.plugin.actions;

import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.project.Project
import com.itangcent.intellij.context.ActionContext
import org.slf4j.LoggerFactory


/**
 * CommonApiExportAction
 *
 * @author iimik
 * @since 2024-12-29 12:43
 **/
class CommonApiExportAction: ApiExportAction("导出API") {

    private val logger = LoggerFactory.getLogger(CommonApiExportAction::class.java)

    override fun actionPerformed(actionContext: ActionContext, project: Project?, anActionEvent: AnActionEvent) {
        super.actionPerformed(actionContext, project, anActionEvent)
    }

    override fun actionPerformed(event: AnActionEvent) {
        logger.info("开始导出API... ")
    }

}