package org.ifinalframework.plugins.jetbrains.aio.service.kotlin;

import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnKotlin
import org.ifinalframework.plugins.jetbrains.aio.service.MethodLineMarkerService
import org.jetbrains.kotlin.psi.KtFunction


/**
 * KotlinMethodLineMarkerService
 *
 * @author iimik
 * @since 0.0.1
 **/
@ConditionOnKotlin
class KotlinMethodLineMarkerService : MethodLineMarkerService {
    override fun isMethodLine(element: PsiElement): Boolean? {
        return false
    }

    override fun getMethod(element: PsiElement): PsiElement? {
        return if (element is LeafPsiElement && element.parent is KtFunction) {
            element.parent
        } else null
    }
}