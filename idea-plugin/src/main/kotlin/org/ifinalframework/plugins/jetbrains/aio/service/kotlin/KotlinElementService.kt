package org.ifinalframework.plugins.jetbrains.aio.service.kotlin;

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnKotlin
import org.ifinalframework.plugins.jetbrains.aio.service.ElementService
import org.jetbrains.kotlin.psi.KtAnnotated
import org.jetbrains.kotlin.psi.KtFunction
import org.springframework.stereotype.Component


/**
 * KotlinElementService
 *
 * @author iimik
 * @since 1.6.0
 **/
@Component
@ConditionOnKotlin
class KotlinElementService : ElementService {
    override fun getMethodElement(element: PsiElement): PsiElement? {
        return if (element is KtFunction) {
            element
        } else if (element is LeafPsiElement && element.parent is KtFunction) {
            return element.parent
        } else null
    }

    override fun hasAnyAnnotation(element: PsiElement, anns: Collection<String>): Boolean? {
        return if (element is KtAnnotated) {
            element.annotations.stream().anyMatch { annotation -> anns.any { ann -> ann == annotation.name } }
        } else false
    }
}