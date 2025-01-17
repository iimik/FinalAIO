package org.ifinalframework.jetbrains.plugins.aio.api.idea.util;


import com.google.inject.ImplementedBy;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

/**
 * DocHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultDocHelper.class)
public interface DocHelper {

    String getHeadline(@NotNull PsiElement element);

}
