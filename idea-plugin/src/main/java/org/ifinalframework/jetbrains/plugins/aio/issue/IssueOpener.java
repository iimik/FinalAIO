package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.psi.PsiElement;

/**
 * IssueOpener
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface IssueOpener {

    void open(PsiElement element);

}
