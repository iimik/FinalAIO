package org.ifinalframework.jetbrains.plugins.aio.api.markdown;

import com.intellij.notification.NotificationDisplayType
import com.intellij.notification.NotificationType
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.module.Module
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.search.FilenameIndex
import com.intellij.psi.search.GlobalSearchScope
import org.apache.commons.lang3.StringUtils
import org.ifinalframework.jetbrains.plugins.aio.`$`
import org.ifinalframework.jetbrains.plugins.aio.api.service.MarkdownService
import org.ifinalframework.jetbrains.plugins.aio.application.ElementHandler
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication
import org.ifinalframework.jetbrains.plugins.aio.idea.*
import org.ifinalframework.jetbrains.plugins.aio.service.DocService
import org.slf4j.LoggerFactory
import org.springframework.boot.context.properties.EnableConfigurationProperties
import java.io.IOException
import javax.annotation.Resource


/**
 * MarkdownOpenApplication
 *
 * @issue 1
 * @author iimik
 * @since 0.0.1
 **/
@ElementApplication(
    [
        DocService::class,
        MarkdownService::class,
        DefaultModuleHelper::class,
        DefaultFileCreator::class,
        DefaultNotificationService::class
    ]
)
@EnableConfigurationProperties(MarkdownProperties::class)
class MarkdownOpenApplication : ElementHandler {

    private val logger = LoggerFactory.getLogger(MarkdownOpenApplication::class.java)

    @Resource
    private lateinit var module: Module

    @Resource
    private lateinit var markdownService: MarkdownService

    @Resource
    private lateinit var fileCreator: FileCreator

    @Resource
    private lateinit var notificationService: NotificationService

    override fun handle(element: PsiElement) {

        val markdownFile = getMarkdownFile(element) ?: return

        val editorManager = FileEditorManager.getInstance(module!!.project)
        editorManager.openFile(markdownFile, true)
    }

    /**
     * @issue 9
     */
    private fun getMarkdownFile(element: PsiElement): VirtualFile? {
        val markdownPath = markdownService.getMarkdownFilePath(element) ?: return null

        val files = `$`.read.compute {
            FilenameIndex.getAllFilesByExt(
                module!!.project, "md", GlobalSearchScope.everythingScope(module!!.project)
            )
        }
        var markdownFile =
            files.stream().filter { it: VirtualFile? -> it!!.path.endsWith(markdownPath) }.findFirst().orElse(null)


        if (markdownFile == null) {
            val path = StringUtils.substringBeforeLast(markdownPath, "/")
            val fileName = StringUtils.substringAfterLast(markdownPath, "/")
            try {
                markdownFile = fileCreator!!.createModuleFile(path, fileName)
                notificationService!!.notify(
                    NotificationDisplayType.TOOL_WINDOW, "创建Markdown文件：$fileName", NotificationType.INFORMATION
                )
            } catch (e: IOException) {
                logger.error("create markdown file error: {}", markdownPath, e)
            }
        }

        return markdownFile
    }
}