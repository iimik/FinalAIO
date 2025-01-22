package org.ifinalframework.jetbrains.plugins.aio.git;


import com.intellij.psi.PsiElement;
import lombok.SneakyThrows;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.eclipse.jgit.api.Git;
import org.eclipse.jgit.transport.RemoteConfig;
import org.eclipse.jgit.transport.URIish;
import org.jetbrains.annotations.NotNull;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.List;

/**
 * DefaultGitHelper
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
public class DefaultGitHelper implements GitHelper {
    @Override
    @SneakyThrows
    public String getIssuesUrl(@NotNull PsiElement element, @NotNull String issue) {
        final String basePath = element.getProject().getBasePath();
        return getIssuesUrl(basePath, issue);
    }

    @Override
    @SneakyThrows
    public String getIssuesUrl(@NotNull String gitRepositoryDir, @NotNull String issue) {

        final Git git = Git.open(new File(gitRepositoryDir));
        final List<RemoteConfig> remoteConfigs = git.remoteList().call();
        if (CollectionUtils.isEmpty(remoteConfigs)) {
            return null;
        }

        final URIish uri = remoteConfigs.get(0).getURIs().get(0);
        final String host = uri.getHost();
        final String path = StringUtils.substringBeforeLast(uri.getPath(), ".git");

        if (host.contains("github.com")) {
            return "https://github.com/" + path + "/issues/" + issue;
        } else {
            //https://host/psth/-/issues/225
            return "https://" + host + "/" + path + "/-/issues/" + issue;
        }
    }
}
