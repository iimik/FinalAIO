package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnKotlin
import org.jetbrains.kotlin.kdoc.psi.impl.KDocTag
import org.springframework.stereotype.Component
import java.util.*


/**
 * KotlinIssueDocTagPredicate
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnKotlin
class KotlinIssueDocTagPredicate : IssueDocTagPredicate {
    override fun test(element: PsiElement): Boolean {
        if (element !is KDocTag) {
            return false
        }

        val name = element.name
        return Objects.nonNull(IssueType.ofNullable(name))
    }
}