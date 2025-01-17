package org.ifinalframework.jetbrains.plugins.aio.api.idea.util;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

/**
 * DefaultMarkdownHelper
 *
 * @author iimik
 * @since 1.6.0
 **/
@Singleton
public class DefaultMarkdownHelper implements MarkdownHelper {

    private static final String MARKDOWN_FILE_DIR = "docs/api";

    @Inject
    private DocHelper docHelper;

    @Override
    @Nullable
    public String getMarkdownPath(@NotNull PsiElement element) {
        if (element instanceof PsiMethod) {
            final PsiMethod method = (PsiMethod) element;
            final PsiClass psiClass = method.getContainingClass();
            return buildMarkdownPath(psiClass, method);
        } else if (element instanceof PsiClass) {
            final PsiClass psiClass = (PsiClass) element;
            return buildMarkdownPath(psiClass, null);
        }
        return null;
    }

    private String buildMarkdownPath(@NotNull PsiClass psiClass, @Nullable PsiMethod psiMethod) {
        final String markdownDir = Optional.ofNullable(docHelper.getHeadline(psiClass)).map(it -> it.replace("-", "/")).orElse(null);
        final String markdownName = getMarkdownName(psiMethod);
        return String.join("/", MARKDOWN_FILE_DIR, markdownDir, markdownName) + ".md";
    }

    private String getMarkdownName(@Nullable PsiMethod psiMethod) {
        if (psiMethod == null) {
            return "README";
        }

        final String headline = docHelper.getHeadline(psiMethod);
        if (headline == null || headline.isEmpty()) {
            return psiMethod.getName();
        }

        return headline;

    }

}
