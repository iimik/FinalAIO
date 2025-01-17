package org.ifinalframework.jetbrains.plugins.aio.api.idea.util;


import java.util.Map;
import java.util.ServiceLoader;
import java.util.concurrent.ConcurrentHashMap;

/**
 * ServiceHelper
 *
 * @author iimik
 * @since 1.6.0
 **/
public class ServiceHelper {
    private static final Map<Class, Object> cache = new ConcurrentHashMap<>();


    public static <T> T getInstance(final Class<T> clazz) {
        return (T) cache.computeIfAbsent(clazz, k -> ServiceLoader.load(clazz, clazz.getClassLoader()).findFirst().orElseThrow());
    }
}
