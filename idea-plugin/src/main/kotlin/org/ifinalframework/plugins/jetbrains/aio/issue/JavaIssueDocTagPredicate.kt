package org.ifinalframework.plugins.jetbrains.aio.issue;

import com.intellij.psi.PsiElement
import com.intellij.psi.javadoc.PsiDocTag
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnJava
import org.springframework.stereotype.Component
import java.util.*


/**
 * JavaIssueDocTagPredicate
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
@ConditionOnJava
class JavaIssueDocTagPredicate : IssueDocTagPredicate {

    override fun test(element: PsiElement): Boolean {
        if (element !is PsiDocTag) {
            return false;
        }

        val name = element.name

        return Objects.nonNull(IssueType.ofNullable(name))
    }
}