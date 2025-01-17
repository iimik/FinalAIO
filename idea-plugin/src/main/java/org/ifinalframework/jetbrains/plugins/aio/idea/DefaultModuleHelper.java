package org.ifinalframework.jetbrains.plugins.aio.idea;


import com.google.inject.Singleton;
import com.intellij.openapi.module.Module;

import org.apache.commons.lang3.StringUtils;

/**
 * DefaultModuleHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
public class DefaultModuleHelper implements ModuleHelper {
    @Override
    public String getBasePath(Module module) {
        final String moduleFilePath = module.getModuleFilePath();
        return StringUtils.substringBeforeLast(moduleFilePath, "/");
    }
}
