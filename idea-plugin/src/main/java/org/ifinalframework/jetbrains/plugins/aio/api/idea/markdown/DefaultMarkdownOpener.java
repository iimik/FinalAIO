package org.ifinalframework.jetbrains.plugins.aio.api.idea.markdown;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.MarkdownHelper;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;

/**
 * 在编辑器中打开Markdown
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
public class DefaultMarkdownOpener implements MarkdownOpener {

    @Inject
    private MarkdownHelper markdownHelper;

    @Override
    public void open(@NotNull PsiElement element) {

        if (!(element instanceof PsiClass) && !(element instanceof PsiMethod)) {
            return;
        }


        final String markdownPath = markdownHelper.getMarkdownPath(element);
        if (markdownPath == null) {
            return;
        }

        final Module module = ModuleUtil.findModuleForPsiElement(element);


        final VirtualFile markdownFile = getMarkdownFile(module, markdownPath);

        if (markdownFile == null) {
            return;
        }

        FileEditorManager.getInstance(module.getProject()).openFile(markdownFile, true);

    }

    @Nullable
    private VirtualFile getMarkdownFile(@NotNull Module module, String markdownPath) {
        final Collection<VirtualFile> files = FilenameIndex.getAllFilesByExt(module.getProject(), "md", GlobalSearchScope.everythingScope(module.getProject()));
        return files.stream()
                .filter(it -> it.getPath().endsWith(markdownPath))
                .findFirst().orElse(null);
    }

}
