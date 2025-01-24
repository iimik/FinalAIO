package org.ifinalframework.plugins.jetbrains.aio.idea.spring;

import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

import java.net.URL;

import static org.junit.jupiter.api.Assertions.*;

/**
 * UrlResourceTest
 *
 * @author iimik
 * @since 0.0.1
 **/
class UrlResourceTest {

    @Test
    @SneakyThrows
    void test(){
        final URL url = new URL(new URL("file:///Users/iimik/Workspaces/iimik/FinalAIO/idea-plugin/src/main/java/org/ifinalframework/jetbrains/plugins/aio/idea"), "spring");
        final UrlResource resource = new UrlResource(new URL("file:///Users/iimik/Workspaces/iimik/FinalAIO/idea-plugin/src/main/java/org/ifinalframework/jetbrains/plugins/aio/idea"));
        final Resource resourceRelative = resource.createRelative("spring");
        System.out.println();
    }

}