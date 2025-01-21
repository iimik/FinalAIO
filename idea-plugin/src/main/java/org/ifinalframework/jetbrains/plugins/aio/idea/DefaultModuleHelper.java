package org.ifinalframework.jetbrains.plugins.aio.idea;


import com.google.inject.Singleton;
import com.intellij.openapi.module.Module;

import com.intellij.openapi.project.ProjectUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

/**
 * DefaultModuleHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
@Component
public class DefaultModuleHelper implements ModuleHelper {
    @Override
    public String getBasePath(Module module) {
        return ProjectUtil.guessModuleDir(module).getPath();
    }
}
