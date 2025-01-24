package org.ifinalframework.jetbrains.plugins.aio.util;

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiIdentifier
import com.intellij.psi.PsiMethod
import org.jetbrains.kotlin.psi.KtFunction
import org.jetbrains.uast.UIdentifier
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.toUElement
import java.util.stream.Stream


/**
 * ElementExt
 *
 * @author iimik
 * @since 0.0.1
 **/
object ElementKt {

    private const val REQUEST_MAPPING_ANNOTATION: String = "org.springframework.web.bind.annotation.RequestMapping"
    private const val GET_MAPPING: String = "org.springframework.web.bind.annotation.GetMapping"
    private const val POST_MAPPING: String = "org.springframework.web.bind.annotation.PostMapping"
    private const val PUT_MAPPING: String = "org.springframework.web.bind.annotation.PutMapping"
    private const val DELETE_MAPPING: String = "org.springframework.web.bind.annotation.DeleteMapping"
    private const val PATCH_MAPPING: String = "org.springframework.web.bind.annotation.PatchMapping"

    /**
     * @see <a href="https://plugins.jetbrains.com/docs/intellij/line-marker-provider.html#best-practices-for-implementing-line-marker-providers">Best Practices for Implementing Line Marker Providers</a>
     */
    fun PsiElement.isMethodLine(): Boolean {

        val language = this.language.id

        return if ("java".equals(language, ignoreCase = true)) {
            this is PsiIdentifier && this.parent is PsiMethod
        } else if ("kotlin".equals(language, ignoreCase = true)) {

            val uElement = this.toUElement()
            uElement is UIdentifier && uElement.uastParent is UMethod

        } else {
            false
        }


    }

    fun PsiMethod.isApiMethod(): Boolean {

        return Stream.of<String>(
            REQUEST_MAPPING_ANNOTATION,
            GET_MAPPING,
            POST_MAPPING,
            PUT_MAPPING,
            DELETE_MAPPING,
            PATCH_MAPPING
        ).map { this.hasAnnotation(it) }
            .filter { it }
            .findFirst()
            .isPresent
    }
}