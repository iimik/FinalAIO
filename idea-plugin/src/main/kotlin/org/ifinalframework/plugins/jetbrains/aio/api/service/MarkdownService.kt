package org.ifinalframework.plugins.jetbrains.aio.api.service

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.api.service.java.JavaMarkdownService
import org.ifinalframework.plugins.jetbrains.aio.api.service.kotlin.KotlinMarkdownService
import org.ifinalframework.plugins.jetbrains.aio.core.annotation.LanguageSpi


/**
 * MarkdownService
 *
 * @author iimik
 * @since 0.0.1
 **/
@LanguageSpi(
    JavaMarkdownService::class,
    KotlinMarkdownService::class,
)
@FunctionalInterface
interface MarkdownService {
    fun getMarkdownFilePath(element: PsiElement): String?
}