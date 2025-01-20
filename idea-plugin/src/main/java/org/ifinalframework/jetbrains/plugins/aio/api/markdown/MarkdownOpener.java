package org.ifinalframework.jetbrains.plugins.aio.api.markdown;


import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ReadAction;
import org.jetbrains.annotations.NotNull;

/**
 * {@code Markdown}打开器
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface MarkdownOpener {
    /**
     * 打开Markdown
     *
     * @param element 可以有Markdown的元素
     */
    @ReadAction
    void open(@NotNull PsiElement element);
}
