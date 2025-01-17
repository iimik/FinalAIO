package org.ifinalframework.jetbrains.plugins.aio.api.idea.action;


import com.google.inject.Guice;
import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.util.NlsActions;
import com.intellij.psi.PsiElement;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.$;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.markdown.MarkdownOpener;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.markdown.OpenMarkdownModule;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.Icon;

/**
 * OpenMarkdownAction
 *
 * @author iimik
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
    public void actionPerformed(@NotNull AnActionEvent anActionEvent) {
        $.runInReadAction(() -> {
            final PsiElement psiElement = anActionEvent.getData(CommonDataKeys.PSI_ELEMENT);
            final MarkdownOpener opener = Guice.createInjector(new OpenMarkdownModule())
                    .getInstance(MarkdownOpener.class);
            opener.open(psiElement);
        });
    }
}
