package org.ifinalframework.jetbrains.plugins.aio.api.markdown;


import com.intellij.codeInsight.daemon.GutterIconNavigationHandler;
import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiIdentifier;
import com.intellij.psi.PsiMethod;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.icon.Icons;
import org.jetbrains.annotations.NotNull;

import java.awt.event.MouseEvent;
import java.util.Objects;
import java.util.stream.Stream;

/**
 * 打开Markdown
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
public class MarkdownLineMarkerProvider implements LineMarkerProvider {

    private static final String REQUEST_MAPPING_ANNOTATION = "org.springframework.web.bind.annotation.RequestMapping";
    private static final String GET_MAPPING = "org.springframework.web.bind.annotation.GetMapping";
    private static final String POST_MAPPING = "org.springframework.web.bind.annotation.PostMapping";
    private static final String PUT_MAPPING = "org.springframework.web.bind.annotation.PutMapping";
    private static final String DELETE_MAPPING = "org.springframework.web.bind.annotation.DeleteMapping";
    private static final String PATCH_MAPPING = "org.springframework.web.bind.annotation.PatchMapping";


    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement psiElement) {
        if (psiElement instanceof PsiIdentifier && psiElement.getParent() instanceof PsiMethod) {

            final PsiMethod method = (PsiMethod) psiElement.getParent();

            final boolean present = Stream.of(REQUEST_MAPPING_ANNOTATION, GET_MAPPING, POST_MAPPING, PUT_MAPPING, DELETE_MAPPING, PATCH_MAPPING)
                    .map(it -> method.getAnnotation(it))
                    .filter(Objects::nonNull)
                    .findFirst()
                    .isPresent();

            if (!present) {
                return null;
            }

            final NavigationGutterIconBuilder builder = NavigationGutterIconBuilder.create(Icons.MARKDOWN);
            builder.setTargets(psiElement);
            builder.setTooltipText("打开Markdown文件");
            return builder.createLineMarkerInfo(psiElement, new GutterIconNavigationHandler<PsiElement>() {
                @Override
                public void navigate(MouseEvent mouseEvent, PsiElement psiElement) {
                    $.run(MarkdownOpenElementApplication.class, psiElement.getParent());
                }
            });

        }

        return null;
    }


}
