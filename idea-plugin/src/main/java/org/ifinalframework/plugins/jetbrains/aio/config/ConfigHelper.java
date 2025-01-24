package org.ifinalframework.plugins.jetbrains.aio.config;


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
 * @since 0.0.1
 **/
@ImplementedBy(DefaultConfigHelper.class)
public interface ConfigHelper {

    default List<String> getConfigPaths(@NotNull PsiElement element){
        return getConfigPaths(ModuleUtil.findModuleForPsiElement(element));
    }

    List<String> getConfigPaths(@NotNull Module module);
}
