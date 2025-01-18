package org.ifinalframework.jetbrains.plugins.aio.issue;


import com.intellij.openapi.util.IconLoader;

import org.ifinalframework.jetbrains.plugins.aio.icon.Icons;

import javax.swing.Icon;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;

/**
 * IssueType
 *
 * @author iimik
 * @since 1.6.0
 **/
@Getter
@RequiredArgsConstructor
public enum IssueType {
    issue(IconLoader.getIcon("/assets/git.svg", Icons.class)),
    jira(IconLoader.getIcon("/assets/jira.svg", Icons.class));

    private final Icon icon;


    public static IssueType of(final String name) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
}
