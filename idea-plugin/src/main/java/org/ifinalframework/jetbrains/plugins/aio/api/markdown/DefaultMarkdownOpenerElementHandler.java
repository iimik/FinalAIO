package org.ifinalframework.jetbrains.plugins.aio.api.markdown;


import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Collection;

/**
 * 在编辑器中打开Markdown
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
@Component
public class DefaultMarkdownOpenerElementHandler implements ElementHandler {

    @Resource
    private Module module;
    @Resource
    private MarkdownHelper markdownHelper;

    @Override
    public void handle(@NotNull PsiElement element) {

        if (!(element instanceof PsiClass) && !(element instanceof PsiMethod)) {
            return;
        }

        final String markdownPath = markdownHelper.getMarkdownPath(element);
        if (markdownPath == null) {
            return;
        }


        final VirtualFile markdownFile = getMarkdownFile(module, markdownPath);

        if (markdownFile == null) {
            return;
        }

        final FileEditorManager editorManager = FileEditorManager.getInstance(module.getProject());
        editorManager.openFile(markdownFile, true);

    }

    @Nullable
    private VirtualFile getMarkdownFile(@NotNull Module module, String markdownPath) {
        final Collection<VirtualFile> files = $.read.compute(() -> FilenameIndex.getAllFilesByExt(module.getProject(), "md", GlobalSearchScope.everythingScope(module.getProject())));
        return files.stream()
                .filter(it -> it.getPath().endsWith(markdownPath))
                .findFirst().orElse(null);
    }

}
