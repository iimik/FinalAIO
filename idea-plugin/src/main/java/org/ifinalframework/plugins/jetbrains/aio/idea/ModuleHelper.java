package org.ifinalframework.plugins.jetbrains.aio.idea;


import com.google.inject.ImplementedBy;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiElement;

import org.gradle.internal.impldep.org.eclipse.jgit.annotations.NonNull;
import org.ifinalframework.plugins.jetbrains.aio.application.annotation.ReadAction;
import org.jetbrains.annotations.NotNull;

/**
 * ModuleHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultModuleHelper.class)
public interface ModuleHelper {

    @ReadAction
    default String getBasePath(@NotNull PsiElement element) {
        return getBasePath(ModuleUtil.findModuleForPsiElement(element));
    }

    @ReadAction
    String getBasePath(@NonNull Module module);

}
