package org.ifinalframework.jetbrains.plugins.aio.browser;


import com.google.inject.ImplementedBy;

/**
 * BrowserOpener
 *
 * @author iimik
 * @since 0.0.1
 **/
@ImplementedBy(DefaultBrowserOpener.class)
public interface BrowserOpener {
    void open(String url);
}
