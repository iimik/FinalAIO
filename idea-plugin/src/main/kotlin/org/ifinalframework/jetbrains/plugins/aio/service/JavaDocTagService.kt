package org.ifinalframework.jetbrains.plugins.aio.service;

import com.intellij.psi.PsiElement
import com.intellij.psi.javadoc.PsiDocTag
import org.ifinalframework.jetbrains.plugins.aio.application.condition.ConditionOnJava
import org.springframework.stereotype.Component


/**
 * JavaDocTagService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnJava
class JavaDocTagService : DocTagService {
    override fun isDocTag(element: PsiElement): Boolean {
        return element is PsiDocTag
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
}