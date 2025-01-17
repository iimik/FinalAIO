package org.ifinalframework.jetbrains.plugins.aio.config;


import com.google.inject.ImplementedBy;
import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.psi.PsiElement;

import org.jetbrains.annotations.NotNull;

import java.util.List;

/**
 * ConfigHelper
 *
 * @author iimik
 * @since 1.6.0
 **/
@ImplementedBy(DefaultConfigHelper.class)
public interface ConfigHelper {

    default List<String> getConfigPaths(@NotNull PsiElement element){
        return getConfigPaths(ModuleUtil.findModuleForPsiElement(element));
    }

    List<String> getConfigPaths(@NotNull Module module);
}
