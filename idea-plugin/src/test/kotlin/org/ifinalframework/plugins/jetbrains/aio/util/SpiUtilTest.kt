package org.ifinalframework.plugins.jetbrains.aio.util

import org.ifinalframework.plugins.jetbrains.aio.issue.IssueDocTagPredicate
import org.ifinalframework.plugins.jetbrains.aio.service.DocService
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