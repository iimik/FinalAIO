package org.ifinalframework.jetbrains.plugins.aio.api.markdown;


import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.search.FilenameIndex;
import com.intellij.psi.search.GlobalSearchScope;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication;
import org.ifinalframework.jetbrains.plugins.aio.idea.DefaultFileCreator;
import org.ifinalframework.jetbrains.plugins.aio.idea.DefaultModuleHelper;
import org.ifinalframework.jetbrains.plugins.aio.idea.FileCreator;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.Collection;

/**
 * MarkdownOpenElementApplication
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
@Slf4j
@ElementApplication({
        DefaultDocHelper.class,
        DefaultMarkdownHelper.class,
        DefaultModuleHelper.class,
        DefaultFileCreator.class
})
public class MarkdownOpenApplication implements ElementHandler {

    @Resource
    private Module module;
    @Resource
    private MarkdownHelper markdownHelper;
    @Resource
    private FileCreator fileCreator;

    @Override
    public void handle(@NotNull PsiElement element) {

        if (!(element instanceof PsiClass) && !(element instanceof PsiMethod)) {
            return;
        }

        final VirtualFile markdownFile = getMarkdownFile(element);
        if (markdownFile == null) {
            return;
        }

        final FileEditorManager editorManager = FileEditorManager.getInstance(module.getProject());
        editorManager.openFile(markdownFile, true);

    }

    /**
     * @issue 9
     */
    @Nullable
    private VirtualFile getMarkdownFile(PsiElement element) {

        final String markdownPath = markdownHelper.getMarkdownPath(element);
        if (markdownPath == null) {
            return null;
        }

        final Collection<VirtualFile> files = $.read.compute(() -> FilenameIndex.getAllFilesByExt(module.getProject(), "md", GlobalSearchScope.everythingScope(module.getProject())));
        VirtualFile markdownFile = files.stream()
                .filter(it -> it.getPath().endsWith(markdownPath))
                .findFirst().orElse(null);


        if (markdownFile == null) {
            final String path = StringUtils.substringBeforeLast(markdownPath, "/");
            final String fileName = StringUtils.substringAfterLast(markdownPath, "/");
            try {
                markdownFile = fileCreator.createModuleFile(path, fileName);
            } catch (IOException e) {
                logger.error("create markdown file error: {}", markdownPath, e);
            }
        }

        return markdownFile;

    }
}