package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.codeInsight.daemon.LineMarkerInfo;
import com.intellij.codeInsight.daemon.LineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.psi.PsiElement;
import com.intellij.psi.javadoc.PsiDocTag;
import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;

/**
 * Issue行标记提供者
 *
 * <h4>支持文档注释标签</h4>
 * <ul>
 *     <li>{@code @jira} Jira 项目管理</li>
 *     <li>{@code @issue} Git issue 管理</li>
 * </ul>
 *
 * @author iimik
 * @issue 3
 * @jira 3
 * @since 0.0.1
 **/
@Slf4j
public class IssueLineMarkerProvider implements LineMarkerProvider {

    @Override
    public LineMarkerInfo<?> getLineMarkerInfo(@NotNull PsiElement psiElement) {
        if (psiElement instanceof PsiDocTag) {
            final PsiDocTag tag = (PsiDocTag) psiElement;

            final IssueType issueType = IssueType.of(tag.getName());

            if (Objects.isNull(issueType)) {
                return null;
            }

            final NavigationGutterIconBuilder builder = NavigationGutterIconBuilder.create(issueType.getIcon());
            builder.setTargets(psiElement);
            builder.setTooltipText("打开Jira");
            return builder.createLineMarkerInfo(psiElement, ((mouseEvent, element) -> {
                $.run(new IssueLineMarkerApplication(), element);
            }));

        }

        return null;
    }

}
