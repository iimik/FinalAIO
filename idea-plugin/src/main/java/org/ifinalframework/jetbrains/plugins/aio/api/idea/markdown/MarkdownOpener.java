package org.ifinalframework.jetbrains.plugins.aio.api.idea.markdown;


import com.google.inject.ImplementedBy;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

/**
 * {@code Markdown}打开器
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultMarkdownOpener.class)
public interface MarkdownOpener {
    /**
     * 打开Markdown
     *
     * @param element 可以有Markdown的元素
     */
    void open(@NotNull PsiElement element);
}
