package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.psi.PsiElement;

/**
 * ElementApplicationRunner
 *
 * @author iimik
 * @since 1.6.0
 **/
@FunctionalInterface
public interface ElementApplicationRunner {
    void run(ElementApplication application, PsiElement element);
}
