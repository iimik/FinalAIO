package org.ifinalframework.jetbrains.plugins.aio.api.idea.util;


import com.google.inject.Singleton;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.intellij.psi.javadoc.PsiDocComment;
import com.intellij.psi.javadoc.PsiDocTag;

import org.ifinalframework.jetbrains.plugins.aio.$;
import org.springframework.stereotype.Component;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

/**
 * DefaultDocHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
@Component
public class DefaultDocHelper implements DocHelper {

    @Override
    public String getHeadline(@NotNull PsiElement element) {
        final PsiDocComment docComment = getDocComment(element);

        if (docComment == null) {
            return null;
        }

        final PsiElement[] descriptionElements = docComment.getDescriptionElements();
        return Arrays.stream(descriptionElements)
                .map(it -> it.getText().trim())
                .filter(it -> !it.isBlank())
                .findFirst().orElse(null);

    }

    private PsiDocComment getDocComment(@NotNull PsiElement element) {
        if (element instanceof PsiDocComment) {
            return (PsiDocComment) element;
        } else if (element instanceof PsiClass) {
            return ((PsiClass) element).getDocComment();
        } else if (element instanceof PsiMethod) {
            return ((PsiMethod) element).getDocComment();
        }

        return null;
    }

    @Override
    public String getDocTagValue(@NotNull PsiDocTag psiDocTag) {
        return $.read.compute(() -> psiDocTag.getValueElement().getText().trim());
    }
}
