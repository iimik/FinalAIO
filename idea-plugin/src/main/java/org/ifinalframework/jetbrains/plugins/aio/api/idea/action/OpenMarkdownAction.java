package org.ifinalframework.jetbrains.plugins.aio.api.idea.action;


import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.util.NlsActions;
import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.api.markdown.MarkdownOpenApplication;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadAction;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

/**
 * OpenMarkdownAction
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
public class OpenMarkdownAction extends AnAction {
    public OpenMarkdownAction() {
    }

    public OpenMarkdownAction(@Nullable Icon icon) {
        super(icon);
    }

    public OpenMarkdownAction(@Nullable @NlsActions.ActionText String text) {
        super(text);
    }

    @Override
    @ReadAction
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        final PsiElement psiElement = anActionEvent.getData(CommonDataKeys.PSI_ELEMENT);
        $.run(MarkdownOpenApplication.class, psiElement);
    }
}
