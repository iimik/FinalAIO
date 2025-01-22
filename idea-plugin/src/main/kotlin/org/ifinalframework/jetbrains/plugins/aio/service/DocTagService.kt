package org.ifinalframework.jetbrains.plugins.aio.service

import com.intellij.psi.PsiElement
import org.ifinalframework.jetbrains.plugins.aio.core.annotation.LanguageSpi


/**
 * DocService
 *
 * @author iimik
 * @since 0.0.1
 **/
@LanguageSpi(
    [
        JavaDocTagService::class,
        KotlinDocTagService::class
    ]
)
interface DocTagService {
    fun isDocTag(element: PsiElement): Boolean

    fun getTagName(element: PsiElement): String?

    fun getTagValue(element: PsiElement): String?
}