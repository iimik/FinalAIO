package org.ifinalframework.jetbrains.plugins.aio.api.markdown

import com.google.inject.ImplementedBy
import com.intellij.psi.PsiElement


/**
 * MarkdownHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultMarkdownHelper::class)
interface MarkdownHelper {
    fun getMarkdownPath(element: PsiElement): String?
}