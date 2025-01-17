package org.ifinalframework.jetbrains.plugins.aio.api.idea.markdown;


import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;

import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultDocHelper;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DefaultMarkdownHelper;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.DocHelper;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.util.MarkdownHelper;
import org.ifinalframework.jetbrains.plugins.aio.api.idea.action.OpenMarkdownAction;

/**
 * OpenMarkdownModule
 *
 * @author iimik
 * @see OpenMarkdownAction
 * @see MarkdownLineMarkerProvider
 * @since 1.6.0
 **/
public class OpenMarkdownModule extends AbstractModule {
    @Override
    protected void configure() {
        bind(DocHelper.class).to(DefaultDocHelper.class);
        bind(MarkdownHelper.class).to(DefaultMarkdownHelper.class);
        bind(MarkdownOpener.class).to(DefaultMarkdownOpener.class);
    }

    public static Injector injector() {
        return Guice.createInjector(new OpenMarkdownModule());
    }

}
