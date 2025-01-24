package org.ifinalframework.plugins.jetbrains.aio.api.markdown;

import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.`$`
import org.ifinalframework.plugins.jetbrains.aio.service.MethodLineMarkerService
import org.ifinalframework.plugins.jetbrains.aio.util.SpiUtil
import org.jetbrains.uast.UIdentifier
import org.jetbrains.uast.UMethod
import org.jetbrains.uast.toUElement
import java.util.*
import java.util.stream.Collectors
import java.util.stream.Stream


/**
 * 打开Markdown
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
class MarkdownLineMarkerProvider : LineMarkerProvider {

    private val methodLineMarkerService = SpiUtil.languageSpi(MethodLineMarkerService::class)

    override fun getLineMarkerInfo(psiElement: PsiElement): LineMarkerInfo<*>? {

        val uElement = psiElement.toUElement()

        return if (uElement is UIdentifier && uElement.uastParent is UMethod) {
            val umethod = uElement.uastParent as UMethod

            val anns = Stream.of(
                REQUEST_MAPPING_ANNOTATION,
                GET_MAPPING,
                POST_MAPPING,
                PUT_MAPPING,
                DELETE_MAPPING,
                PATCH_MAPPING
            ).collect(Collectors.toList())
            val present = anns.stream()
                .map { it: String? -> umethod.getAnnotation(it!!) }
                .filter { obj: PsiAnnotation? -> Objects.nonNull(obj) }
                .findFirst()
                .isPresent

            if (!present) {
                return null
            }

            val method = methodLineMarkerService.getMethod(psiElement)

            val builder: NavigationGutterIconBuilder<PsiElement> =
                NavigationGutterIconBuilder.create(org.ifinalframework.plugins.jetbrains.aio.icon.Icons.MARKDOWN)
            builder.setTargets(psiElement)
            builder.setTooltipText("打开Markdown文件")
            return builder.createLineMarkerInfo(
                psiElement
            ) { mouseEvent, psiElement ->
                `$`.run(
                    MarkdownOpenApplication::class.java,
                    psiElement.parent
                )
            }
        } else null

    }


    companion object {
        private const val REQUEST_MAPPING_ANNOTATION = "org.springframework.web.bind.annotation.RequestMapping"
        private const val GET_MAPPING = "org.springframework.web.bind.annotation.GetMapping"
        private const val POST_MAPPING = "org.springframework.web.bind.annotation.PostMapping"
        private const val PUT_MAPPING = "org.springframework.web.bind.annotation.PutMapping"
        private const val DELETE_MAPPING = "org.springframework.web.bind.annotation.DeleteMapping"
        private const val PATCH_MAPPING = "org.springframework.web.bind.annotation.PatchMapping"
    }
}