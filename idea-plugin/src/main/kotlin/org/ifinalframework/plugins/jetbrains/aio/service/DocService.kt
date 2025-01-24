package org.ifinalframework.plugins.jetbrains.aio.service

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.core.annotation.LanguageSpi
import org.ifinalframework.plugins.jetbrains.aio.service.java.JavaDocService
import org.ifinalframework.plugins.jetbrains.aio.service.kotlin.KotlinDocService


/**
 * DocService
 *
 * @author iimik
 * @since 0.0.1
 **/
@LanguageSpi(
    JavaDocService::class,
    KotlinDocService::class
)
interface DocService {
    fun isDocTag(element: PsiElement): Boolean

    fun getHeadline(element: PsiElement): String?

    fun getTagName(element: PsiElement): String?

    fun getTagValue(element: PsiElement): String?
}