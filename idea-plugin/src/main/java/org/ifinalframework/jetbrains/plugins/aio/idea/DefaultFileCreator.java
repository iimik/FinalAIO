package org.ifinalframework.jetbrains.plugins.aio.idea;


import com.intellij.openapi.module.Module;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.LocalFileSystem;
import com.intellij.openapi.vfs.VirtualFile;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.Objects;

/**
 * DefaultFileCreator
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
public class DefaultFileCreator implements FileCreator {

    @Resource
    private Project project;
    @Resource
    private Module module;
    @Resource
    private ModuleHelper moduleHelper;

    @Override
    public VirtualFile createModuleFile(String path, String fileName) throws IOException {

        final String basePath = moduleHelper.getBasePath(module);
        final String filePath = basePath + "/" + path;
        new File(filePath).mkdirs();

        final VirtualFile virtualFile = LocalFileSystem.getInstance().refreshAndFindFileByPath(filePath);
        return Objects.requireNonNull(virtualFile).createChildData(project, fileName);
    }
}
