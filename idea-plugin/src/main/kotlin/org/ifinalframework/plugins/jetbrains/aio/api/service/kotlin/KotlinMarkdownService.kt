package org.ifinalframework.plugins.jetbrains.aio.api.service.kotlin;

import com.intellij.psi.PsiElement
import jakarta.annotation.Resource
import org.ifinalframework.plugins.jetbrains.aio.api.markdown.MarkdownProperties
import org.ifinalframework.plugins.jetbrains.aio.api.service.MarkdownService
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnKotlin
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.jetbrains.kotlin.psi.KtClass
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.kotlin.psi.psiUtil.containingClass
import org.springframework.stereotype.Service


/**
 * KotlinMarkdownService
 *
 * @author iimik
 * @since 1.6.0
 **/
@Service
@ConditionOnKotlin
class KotlinMarkdownService : MarkdownService {

    @Resource
    private lateinit var markdownProperties: MarkdownProperties

    @Resource
    private lateinit var docService: DocService

    override fun getMarkdownFilePath(element: PsiElement): String? {
        return when (element) {
            is KtFunction -> {
                val clazz = element.containingClass()
                val category = docService.getHeadline(clazz!!) ?: clazz.name
                val name = docService.getHeadline(element) ?: element.name
                return "${markdownProperties.basePath}/$category/$name.md"
            }

            is KtClass -> {
                val category = docService.getHeadline(element) ?: element.name
                val name = "README"
                return "${markdownProperties.basePath}/$category/$name.md"
            }

            else -> null
        }
    }

}