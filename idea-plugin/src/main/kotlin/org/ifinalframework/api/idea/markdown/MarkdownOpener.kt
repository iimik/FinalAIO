package org.ifinalframework.api.idea.markdown;

import com.google.inject.Inject
import com.google.inject.Singleton
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.module.ModuleUtil
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import com.itangcent.idea.plugin.api.export.core.ApiHelper
import com.itangcent.idea.plugin.api.export.core.FormatFolderHelper
import com.itangcent.intellij.context.ActionContext


/**
 * MarkdownOpener
 *
 * @author iimik
 * @since 2025-01-08 20:43
 **/
@Singleton
class MarkdownOpener {

    @Inject
    private lateinit var formatFolderHelper: FormatFolderHelper

    @Inject
    private lateinit var apiHelper: ApiHelper

    @Inject
    private lateinit var actionContext: ActionContext

    fun open(element: PsiElement) {

        val hasMarkdown = element is PsiMethod || element is PsiClass

        if (!hasMarkdown) {
            return
        }

        val module = ModuleUtil.findModuleForPsiElement(element)
        val project = module!!.project

        val folder = formatFolderHelper!!.resolveFolder(element!!)

        val markdownName = if (element is PsiMethod) apiHelper.nameOfApi(element) else "README"

        var markdownDocs = "docs/api";
        val markdownDir = folder.name!!.replace("-", "/")

        val moduleScope = GlobalSearchScope.moduleScope(module)

        val markdownPath = "$markdownDocs/$markdownDir/${markdownName}.md"

        val editorManager = FileEditorManager.getInstance(project)

        val files = FilenameIndex.getAllFilesByExt(project, "md", GlobalSearchScope.projectScope(project))
        files.stream()
            .filter({ file ->
                file.path.endsWith(markdownPath)
            })
            .findFirst()
            .ifPresent({
                actionContext.runInSwingUI {
                    try {
                        val fileEditor = editorManager.openFile(it, true)
                        fileEditor.size
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }

            })


    }
}