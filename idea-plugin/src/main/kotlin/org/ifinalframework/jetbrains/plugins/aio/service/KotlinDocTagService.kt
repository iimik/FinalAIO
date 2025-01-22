package org.ifinalframework.jetbrains.plugins.aio.service;

import com.intellij.psi.PsiElement
import org.ifinalframework.jetbrains.plugins.aio.application.condition.ConditionOnKotlin
import org.jetbrains.kotlin.kdoc.psi.impl.KDocTag
import org.springframework.stereotype.Component


/**
 * KotlinDocTagService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnKotlin
class KotlinDocTagService : DocTagService {
    override fun isDocTag(element: PsiElement): Boolean {
        return element is KDocTag
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