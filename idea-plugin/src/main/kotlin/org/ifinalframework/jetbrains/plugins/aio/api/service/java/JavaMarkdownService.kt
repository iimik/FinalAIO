package org.ifinalframework.jetbrains.plugins.aio.api.service.java;

import com.intellij.psi.PsiClass
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import jakarta.annotation.Resource
import org.ifinalframework.jetbrains.plugins.aio.api.markdown.MarkdownProperties
import org.ifinalframework.jetbrains.plugins.aio.api.service.MarkdownService
import org.ifinalframework.jetbrains.plugins.aio.application.condition.ConditionOnJava
import org.ifinalframework.jetbrains.plugins.aio.service.DocService
import org.springframework.stereotype.Service


/**
 * JavaMarkdownService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Service
@ConditionOnJava
class JavaMarkdownService : MarkdownService {

    @Resource
    private lateinit var markdownProperties: MarkdownProperties

    @Resource
    private lateinit var docService: DocService

    override fun getMarkdownFilePath(element: PsiElement): String? {
        when (element) {
            is PsiMethod -> {
                val clazz = element.containingClass
                val category = docService.getHeadline(clazz!!) ?: clazz.name
                val name = docService.getHeadline(element) ?: element.name
                return "${markdownProperties.basePath}/$category/$name.md"
            }

            is PsiClass -> {

                val category = docService.getHeadline(element!!) ?: element.name
                val name = "README"
                return "${markdownProperties.basePath}/$category/$name.md"
            }

            else -> return null
        }
    }
}