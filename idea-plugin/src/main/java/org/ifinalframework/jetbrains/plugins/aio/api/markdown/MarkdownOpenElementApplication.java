package org.ifinalframework.jetbrains.plugins.aio.api.markdown;


import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.application.annotation.ElementApplication;

/**
 * MarkdownOpenElementApplication
 *
 * @author iimik
 * @issue 1
 * @since 0.0.1
 **/
@ElementApplication({
        DefaultDocHelper.class,
        DefaultMarkdownOpenerElementHandler.class,
        DefaultMarkdownHelper.class,
})
public class MarkdownOpenElementApplication {
}