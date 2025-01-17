package org.ifinalframework.jetbrains.plugins.aio.api.idea.core

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