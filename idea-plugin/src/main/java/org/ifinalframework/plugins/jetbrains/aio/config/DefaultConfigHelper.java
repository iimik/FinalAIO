package org.ifinalframework.plugins.jetbrains.aio.config;


import com.google.inject.Inject;
import com.google.inject.Singleton;
import com.intellij.openapi.module.Module;

import org.ifinalframework.plugins.jetbrains.aio.idea.DefaultModuleHelper;
import org.ifinalframework.plugins.jetbrains.aio.idea.ModuleHelper;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * DefaultConfigHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
public class DefaultConfigHelper implements ConfigHelper {

    @Inject
    private ModuleHelper moduleHelper = new DefaultModuleHelper();

    @Override
    public List<String> getConfigPaths(@NotNull Module module) {

        final String modulePath = moduleHelper.getBasePath(module);
        final String projectPath = module.getProject().getBasePath();

        String configPath = modulePath;

        List<String> configPaths = new ArrayList<>();

        do {
            configPaths.add(configPath);
            configPath = StringUtils.substringBeforeLast(configPath, "/");

        } while (!configPath.equals(projectPath));

        configPaths.add(projectPath);

        return configPaths;
    }
}
