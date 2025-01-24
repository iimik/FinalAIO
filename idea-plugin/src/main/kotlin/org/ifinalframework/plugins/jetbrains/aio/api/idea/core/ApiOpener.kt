package org.ifinalframework.plugins.jetbrains.aio.api.idea.core

import com.intellij.psi.PsiMethod


/**
 * ApiOpener
 *
 * @author iimik
 * @since 0.0.1
 **/
interface ApiOpener {
    /**
     * 打开API文档
     */
    fun open(method: PsiMethod)
}