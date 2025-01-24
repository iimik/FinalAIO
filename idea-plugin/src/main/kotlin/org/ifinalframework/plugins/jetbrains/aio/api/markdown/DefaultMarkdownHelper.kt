package org.ifinalframework.plugins.jetbrains.aio.api.markdown;

import com.google.inject.Inject
import com.google.inject.Singleton
import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.api.service.MarkdownService
import org.ifinalframework.plugins.jetbrains.aio.application.annotation.ReadAction
import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.stereotype.Component
import javax.annotation.Resource


/**
 * DefaultMarkdownHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
@Component
class DefaultMarkdownHelper : MarkdownHelper {


    @Inject
    @Resource
    private lateinit var markdownService: MarkdownService


    @ReadAction
    override fun getMarkdownPath(element: PsiElement): String? {
        return MARKDOWN_FILE_DIR + "/" + markdownService.getMarkdownFilePath(element)?.replace("-", "/") + ".md"

    }


    companion object {
        private const val MARKDOWN_FILE_DIR = "docs/api"
    }
}