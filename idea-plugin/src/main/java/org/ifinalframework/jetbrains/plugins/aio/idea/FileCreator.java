package org.ifinalframework.jetbrains.plugins.aio.idea;


import com.intellij.openapi.vfs.VirtualFile;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.WriteAction;

import java.io.IOException;

/**
 * FileCreator
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface FileCreator {
    /**
     * 在当前{@link com.intellij.openapi.module.Module}的指定路径{@code path}创建指定名称{@code fileName}的文件。
     *
     * @param path     路径名
     * @param fileName 文件名
     * @return 创建好的文件
     * @throws IOException
     */
    @WriteAction
    VirtualFile createModuleFile(String path, String fileName) throws IOException;
}
