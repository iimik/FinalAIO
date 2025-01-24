package org.ifinalframework.plugins.jetbrains.aio.issue

import com.intellij.openapi.util.IconLoader
import com.intellij.util.containers.stream
import org.ifinalframework.plugins.jetbrains.aio.icon.Icons
import javax.swing.Icon


/**
 * IssueType
 *
 * @author iimik
 * @since 0.0.1
 **/
enum class IssueType(
    val icon: Icon,
) {
    ISSUE(IconLoader.getIcon("/assets/git.svg", org.ifinalframework.plugins.jetbrains.aio.icon.Icons::class.java)),
    JIRA(IconLoader.getIcon("/assets/git.svg", org.ifinalframework.plugins.jetbrains.aio.icon.Icons::class.java));

    companion object {
        fun ofNullable(name: String?): IssueType? {
            return values().stream()
                .filter { v -> v.name.equals(name, ignoreCase = true) }
                .findFirst()
                .orElse(null)
        }
    }
}