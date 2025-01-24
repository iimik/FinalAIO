package org.ifinalframework.plugins.jetbrains.aio.service.java;

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnJava
import org.ifinalframework.plugins.jetbrains.aio.service.MethodLineMarkerService


/**
 * JavaMethodLineMarkerService
 *
 * [Best Practices for Implementing Line Marker Providers﻿](https://plugins.jetbrains.com/docs/intellij/line-marker-provider.html#best-practices-for-implementing-line-marker-providers)
 *
 * @author iimik
 * @since 0.0.1
 * @see [Best Practices for Implementing Line Marker Providers﻿](https://plugins.jetbrains.com/docs/intellij/line-marker-provider.html#best-practices-for-implementing-line-marker-providers)
 **/
@ConditionOnJava
class JavaMethodLineMarkerService : MethodLineMarkerService {
    override fun isMethodLine(element: PsiElement): Boolean? {
        return element is PsiIdentifier && element.parent is PsiMethod
    }

    override fun getMethod(element: PsiElement): PsiElement? {
        return if (element is PsiIdentifier && element.parent is PsiMethod) {
            return element.parent
        } else null
    }
}