package org.ifinalframework.api.idea.action;


import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.itangcent.idea.plugin.actions.ApiExportAction;
import com.itangcent.intellij.context.ActionContext;

import org.ifinalframework.api.idea.parser.DefaultApiParser;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * FinalApiExportAction
 *
 * @author iimik
 * @since 1.6.0
 **/
public class FinalApiExportAction extends ApiExportAction {
    public FinalApiExportAction() {
        super("API 导出");
    }

    @Override
    protected void actionPerformed(@NotNull ActionContext actionContext, @Nullable Project project, @NotNull AnActionEvent anActionEvent) {
        super.actionPerformed(actionContext, project, anActionEvent);
        final PsiElement element = anActionEvent.getData(CommonDataKeys.PSI_ELEMENT);
        actionContext.instance(DefaultApiParser::new).parse(element);
    }
}
