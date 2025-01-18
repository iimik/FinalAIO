package org.ifinalframework.jetbrains.plugins.aio.git;

import org.gradle.internal.impldep.org.eclipse.jgit.api.Git;
import org.gradle.internal.impldep.org.eclipse.jgit.lib.Ref;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.util.Collection;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * GitTest
 *
 * @author iimik
 * @since 1.6.0
 **/
@Slf4j
class GitTest {

    @Test
    @SneakyThrows
    public void test(){
        final Git git = Git.open(new File("/Users/iimik/Workspaces/iimik/FinalAIO"));
        final Collection<Ref> refs = git.lsRemote().call();
        logger.info(refs.toString());
    }

}