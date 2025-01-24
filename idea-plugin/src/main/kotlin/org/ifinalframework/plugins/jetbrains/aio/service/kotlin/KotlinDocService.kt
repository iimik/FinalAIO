package org.ifinalframework.plugins.jetbrains.aio.service.kotlin;

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnKotlin
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.jetbrains.kotlin.kdoc.psi.impl.KDocTag
import org.jetbrains.kotlin.psi.KtDeclaration
import org.springframework.stereotype.Component


/**
 * KotlinDocTagService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnKotlin
class KotlinDocService : DocService {
    override fun isDocTag(element: PsiElement): Boolean {
        return element is KDocTag
    }

    /**
     * @see KtDeclaration.getDocComment
     */
    override fun getHeadline(element: PsiElement): String? {
        return when (element) {
            is KtDeclaration -> {
                val content = element.docComment?.getDefaultSection()?.getContent()?.trimEnd('\n')
                return if (content.isNullOrBlank()) element.name else content
            }

            else -> null
        }
    }

    override fun getTagName(element: PsiElement): String? {
        return if (element is KDocTag) {
            element.name
        } else null
    }

    override fun getTagValue(element: PsiElement): String? {
        return if (element is KDocTag) {
            element.getContent().trim()
        } else null
    }
}