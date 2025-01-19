package org.ifinalframework.jetbrains.plugins.aio;


import com.intellij.psi.PsiElement;

/**
 * ElementPropertySourcesLoader
 *
 * @author iimik
 * @since 0.0.1
 **/
@FunctionalInterface
public interface ElementPropertySourcesLoader {
    void load(ClassLoader classLoader, PsiElement element);
}
