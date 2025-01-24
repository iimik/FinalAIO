package org.ifinalframework.plugins.jetbrains.aio.git;

import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;

import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.nio.file.Path;

import lombok.SneakyThrows;

import static org.junit.jupiter.api.Assertions.*;

/**
 * DefaultGitHelperTest
 *
 * @author iimik
 * @since 0.0.1
 **/
@ExtendWith(MockitoExtension.class)
class DefaultGitHelperTest {

    @Mock
    private PsiElement psiElement;

    @Mock
    private Project project;

    @InjectMocks
    private DefaultGitHelper gitHelper;

    @Test
    @SneakyThrows
    void getIssuesUrl() {

        final String bastPath = StringUtils.substringBefore(Path.of(".").toAbsolutePath().toString(), "/FinalAIO") + "/FinalAIO";

        Mockito.when(psiElement.getProject()).thenReturn(project);
        Mockito.when(project.getBasePath()).thenReturn(bastPath);

        final String issuesUrl = gitHelper.getIssuesUrl(psiElement, "3");
        assertEquals("https://github.com/iimik/FinalAIO/issues/3", issuesUrl);

    }
}