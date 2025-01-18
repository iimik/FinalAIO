package org.ifinalframework.jetbrains.plugins.aio.git;


import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

/**
 * GitHelper
 *
 * @author iimik
 * @since 1.6.0
 **/
public interface GitHelper {
    String getIssuesUrl(@NotNull PsiElement element,@NotNull String issue);

    String getIssuesUrl(@NotNull String gitRepositoryDir,@NotNull String issue);

}
