package org.ifinalframework.jetbrains.plugins.aio.service.java;

import com.intellij.psi.PsiAnnotationOwner
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import com.itangcent.common.utils.firstOrNull
import org.ifinalframework.jetbrains.plugins.aio.application.condition.ConditionOnJava
import org.ifinalframework.jetbrains.plugins.aio.service.ElementService
import org.springframework.stereotype.Component


/**
 * JavaElementService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnJava
class JavaElementService : ElementService {
    override fun getMethodElement(element: PsiElement): PsiElement? {
        return if (element is PsiMethod) {
            element
        } else if (element is PsiIdentifier && element.parent is PsiMethod) {
            element.parent
        } else null
    }

    override fun hasAnyAnnotation(element: PsiElement, anns: Collection<String>): Boolean? {
        return if (element is PsiAnnotationOwner) {
            anns.stream()
                .map { element.hasAnnotation(it) }
                .filter { it }
                .firstOrNull()
        } else false
    }
}