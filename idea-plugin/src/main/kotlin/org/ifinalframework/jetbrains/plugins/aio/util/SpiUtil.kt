package org.ifinalframework.jetbrains.plugins.aio.util;

import org.ifinalframework.jetbrains.plugins.aio.application.condition.ConditionOnLanguage
import org.ifinalframework.jetbrains.plugins.aio.core.LanguageSpiProxy
import org.springframework.core.annotation.AnnotatedElementUtils
import java.lang.reflect.Proxy
import java.util.*
import java.util.function.Consumer
import kotlin.reflect.KClass


/**
 * SpiUtil
 *
 * @author iimik
 * @since 0.0.1
 **/
class SpiUtil {
    companion object {
        fun <T : Any> loadLanguages(kclazz: KClass<T>): Map<String, T> {
            val result = mutableMapOf<String, T>()
            ServiceLoader.load(kclazz.java, kclazz.java.classLoader)
                .forEach(Consumer { item ->
                    val clazz = item.javaClass
                    val annotation = AnnotatedElementUtils.getMergedAnnotation(clazz, ConditionOnLanguage::class.java)
                    val language = annotation.value.lowercase()
                    result[language] = item
                })
            return result
        }


        fun <T : Any> languageSpi(klz: KClass<T>): T {
            val services = loadLanguages(klz)
            val interfaces = arrayOf(klz.java)
            return Proxy.newProxyInstance(klz.java.classLoader, interfaces, LanguageSpiProxy(services)) as T
        }


    }
}
