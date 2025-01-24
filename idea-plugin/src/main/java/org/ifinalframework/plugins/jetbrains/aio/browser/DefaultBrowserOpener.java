package org.ifinalframework.plugins.jetbrains.aio.browser;


import com.google.inject.Singleton;

import org.springframework.stereotype.Component;

import java.awt.Desktop;
import java.net.URI;

/**
 * DefaultBrowserOpener
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
@Component
public class DefaultBrowserOpener implements BrowserOpener {
    @Override
    public void open(String url) {
        try {
            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(new URI(url));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
