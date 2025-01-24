package org.ifinalframework.plugins.jetbrains.aio.git;


import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

/**
 * GitHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface GitHelper {
    String getIssuesUrl(@NotNull PsiElement element,@NotNull String issue);

    String getIssuesUrl(@NotNull String gitRepositoryDir,@NotNull String issue);

}
