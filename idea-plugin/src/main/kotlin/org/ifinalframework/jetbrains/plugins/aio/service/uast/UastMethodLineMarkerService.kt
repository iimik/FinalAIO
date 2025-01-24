package org.ifinalframework.jetbrains.plugins.aio.service.uast;

import com.intellij.psi.PsiElement
import org.ifinalframework.jetbrains.plugins.aio.service.MethodLineMarkerService
import org.jetbrains.uast.UIdentifier
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.toUElement
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean


/**
 * UastMethodLineMarkerService
 *
 * @author iimik
 * @since 0.0.1
 **/
@ConditionalOnMissingBean(MethodLineMarkerService::class)
class UastMethodLineMarkerService : MethodLineMarkerService {
    override fun isMethodLine(element: PsiElement): Boolean? {
        val uElement = element.toUElement()
        return uElement is UIdentifier && uElement.uastParent is UMethod
    }

    override fun getMethod(element: PsiElement): PsiElement? {
        val uElement = element.toUElement()
        return if (uElement is UIdentifier && uElement.uastParent is UMethod) {
            element.parent
        } else null
    }
}