package org.ifinalframework.plugins.jetbrains.aio.service.java;

import com.intellij.psi.*
import com.intellij.psi.javadoc.PsiDocComment
import com.intellij.psi.javadoc.PsiDocTag
import com.itangcent.common.utils.firstOrNull
import com.itangcent.common.utils.notNullOrBlank
import com.itangcent.common.utils.stream
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnJava
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
import org.springframework.stereotype.Component
import java.util.*


/**
 * JavaDocTagService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnJava
class JavaDocService : DocService {
    override fun isDocTag(element: PsiElement): Boolean {
        return element is PsiDocTag
    }

    override fun getHeadline(element: PsiElement): String? {
        val docComment = if (element is PsiDocCommentOwner) element.docComment else return null
        if (Objects.isNull(docComment)) return null
        return docComment?.descriptionElements.stream()
            .map { it.text.trim() }
            .filter { it.notNullOrBlank() }
            .firstOrNull()

    }

    private fun getDocComment(element: PsiElement): PsiDocComment? {
        if (element is PsiDocComment) {
            return element
        } else if (element is PsiClass) {
            return element.docComment
        } else if (element is PsiMethod) {
            return element.docComment
        }

        return null
    }

    override fun getTagName(element: PsiElement): String? {
        return if (element is PsiDocTag) {
            element.name
        } else null
    }

    override fun getTagValue(element: PsiElement): String? {
        return if (element is PsiDocTag) {
            element.valueElement!!.text.trim()
        } else null
    }

    override fun getLineComment(element: PsiElement): String? {
        return if (element is PsiComment) {
            element.text
        } else null
    }
}