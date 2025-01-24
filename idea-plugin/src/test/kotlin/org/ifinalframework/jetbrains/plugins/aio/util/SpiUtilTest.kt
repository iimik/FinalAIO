package org.ifinalframework.jetbrains.plugins.aio.util

import org.ifinalframework.jetbrains.plugins.aio.issue.IssueDocTagPredicate
import org.ifinalframework.jetbrains.plugins.aio.service.DocService
import org.junit.jupiter.api.Assertions.assertNotNull
import org.junit.jupiter.api.Test

/**
 * SpiUtilTest
 *
 * @author iimik
 * @since 0.0.1
 */
class SpiUtilTest {

    @Test
    fun loadLanguages() {
        val languages = SpiUtil.loadLanguages(IssueDocTagPredicate::class)
        assertNotNull(languages)
    }

    @Test
    fun languageSpi(){
        val service = SpiUtil.languageSpi(DocService::class)
        assertNotNull(service)
    }
}