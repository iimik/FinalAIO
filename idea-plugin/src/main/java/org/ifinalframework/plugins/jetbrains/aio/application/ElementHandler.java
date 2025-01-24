package org.ifinalframework.plugins.jetbrains.aio.application;


import com.intellij.psi.PsiElement;
import org.ifinalframework.plugins.jetbrains.aio.application.annotation.EDT;
import org.jetbrains.annotations.NotNull;

/**
 * ElementHandler
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface ElementHandler {
    @EDT
    void handle(@NotNull PsiElement element);
}
