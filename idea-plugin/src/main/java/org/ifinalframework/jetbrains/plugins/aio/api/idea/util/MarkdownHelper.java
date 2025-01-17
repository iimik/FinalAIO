package org.ifinalframework.jetbrains.plugins.aio.api.idea.util;


import com.google.inject.ImplementedBy;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * MarkdownHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultMarkdownHelper.class)
public interface MarkdownHelper {

    @Nullable
    String getMarkdownPath(@NotNull PsiElement element);

}
