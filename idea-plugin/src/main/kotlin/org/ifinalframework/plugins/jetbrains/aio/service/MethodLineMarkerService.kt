package org.ifinalframework.plugins.jetbrains.aio.service

import com.intellij.psi.PsiElement


/**
 * MethodLineMarkerService
 *
 * @author iimik
 * @since 0.0.1
 **/
interface MethodLineMarkerService {
    fun isMethodLine(element: PsiElement): Boolean?

    fun getMethod(element: PsiElement): PsiElement?

}