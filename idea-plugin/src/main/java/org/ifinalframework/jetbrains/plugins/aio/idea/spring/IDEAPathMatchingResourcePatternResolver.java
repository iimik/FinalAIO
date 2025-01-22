package org.ifinalframework.jetbrains.plugins.aio.idea.spring;


import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.util.ResourceUtils;
import org.springframework.util.StringUtils;

import java.io.IOException;
import java.net.JarURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.zip.ZipException;

/**
 * IDEAPathMatchingResourcePatternResolver
 *
 * @author iimik
 * @since 0.0.1
 **/
@Slf4j
public class IDEAPathMatchingResourcePatternResolver extends PathMatchingResourcePatternResolver {
    private final Map<String, NavigableSet<String>> jarEntriesCache = new ConcurrentHashMap<>();

    public IDEAPathMatchingResourcePatternResolver(ClassLoader classLoader) {
        super(classLoader);
    }

    @Override
    protected Set<Resource> doFindPathMatchingJarResources(Resource rootDirResource, URL rootDirUrl, String subPattern)
            throws IOException {

        String jarFileUrl = null;
        String rootEntryPath = "";

        String urlFile = rootDirUrl.getFile();
        int separatorIndex = urlFile.indexOf(ResourceUtils.WAR_URL_SEPARATOR);
        if (separatorIndex == -1) {
            separatorIndex = urlFile.indexOf(ResourceUtils.JAR_URL_SEPARATOR);
        }
        if (separatorIndex != -1) {
            jarFileUrl = urlFile.substring(0, separatorIndex);
            rootEntryPath = urlFile.substring(separatorIndex + 2);  // both separators are 2 chars
            NavigableSet<String> entriesCache = this.jarEntriesCache.get(jarFileUrl);
            if (entriesCache != null) {
                Set<Resource> result = new LinkedHashSet<>(64);
                // Search sorted entries from first entry with rootEntryPath prefix
                for (String entryPath : entriesCache.tailSet(rootEntryPath, false)) {
                    if (!entryPath.startsWith(rootEntryPath)) {
                        // We are beyond the potential matches in the current TreeSet.
                        break;
                    }
                    String relativePath = entryPath.substring(rootEntryPath.length());
                    if (getPathMatcher().match(subPattern, relativePath)) {
                        result.add(getRootDirResourceRelative(rootDirUrl, relativePath));
                    }
                }
                return result;
            }
        }

        URLConnection con = rootDirUrl.openConnection();
        JarFile jarFile;
        boolean closeJarFile;

        if (con instanceof JarURLConnection jarCon) {
            // Should usually be the case for traditional JAR files.
            jarFile = jarCon.getJarFile();
            jarFileUrl = jarCon.getJarFileURL().toExternalForm();
            JarEntry jarEntry = jarCon.getJarEntry();
            rootEntryPath = (jarEntry != null ? jarEntry.getName() : "");
            closeJarFile = !jarCon.getUseCaches();
        }
        else {
            // No JarURLConnection -> need to resort to URL file parsing.
            // We'll assume URLs of the format "jar:path!/entry", with the protocol
            // being arbitrary as long as following the entry format.
            // We'll also handle paths with and without leading "file:" prefix.
            try {
                if (jarFileUrl != null) {
                    jarFile = getJarFile(jarFileUrl);
                }
                else {
                    jarFile = new JarFile(urlFile);
                    jarFileUrl = urlFile;
                    rootEntryPath = "";
                }
                closeJarFile = true;
            }
            catch (ZipException ex) {
                if (logger.isDebugEnabled()) {
                    logger.debug("Skipping invalid jar class path entry [" + urlFile + "]");
                }
                return Collections.emptySet();
            }
        }

        try {
            if (logger.isTraceEnabled()) {
                logger.trace("Looking for matching resources in jar file [" + jarFileUrl + "]");
            }
            if (StringUtils.hasLength(rootEntryPath) && !rootEntryPath.endsWith("/")) {
                // Root entry path must end with slash to allow for proper matching.
                // The Sun JRE does not return a slash here, but BEA JRockit does.
                rootEntryPath = rootEntryPath + "/";
            }
            Set<Resource> result = new LinkedHashSet<>(64);
            NavigableSet<String> entriesCache = new TreeSet<>();
            for (String entryPath : jarFile.stream().map(JarEntry::getName).sorted().toList()) {
                entriesCache.add(entryPath);
                if (entryPath.startsWith(rootEntryPath)) {
                    String relativePath = entryPath.substring(rootEntryPath.length());
                    if (getPathMatcher().match(subPattern, relativePath)) {

                        result.add(getRootDirResourceRelative(rootDirUrl, relativePath));
//                        result.add(rootDirResource.createRelative(relativePath));
                    }
                }
            }
            // Cache jar entries in TreeSet for efficient searching on re-encounter.
            this.jarEntriesCache.put(jarFileUrl, entriesCache);
            return result;
        }
        finally {
            if (closeJarFile) {
                jarFile.close();
            }
        }
    }

    private static @NotNull Resource getRootDirResourceRelative(URL rootDirResource, String relativePath) throws IOException {
        return new UrlResource(new URL(rootDirResource + "/" + relativePath));
    }
}
