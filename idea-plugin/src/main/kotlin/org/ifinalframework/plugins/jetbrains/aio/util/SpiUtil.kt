package org.ifinalframework.plugins.jetbrains.aio.util;

import com.itangcent.common.utils.newInstance
import com.itangcent.common.utils.stream
import org.ifinalframework.plugins.jetbrains.aio.application.condition.ConditionOnLanguage
import org.ifinalframework.plugins.jetbrains.aio.core.LanguageSpiProxy
import org.ifinalframework.plugins.jetbrains.aio.core.annotation.LanguageSpi
import org.springframework.core.annotation.AnnotatedElementUtils
import java.lang.reflect.Proxy
import java.util.*
import java.util.function.Consumer
import java.util.stream.Collectors
import kotlin.reflect.KClass
import kotlin.reflect.full.findAnnotation


/**
 * SpiUtil
 *
 * @author iimik
 * @since 0.0.1
 **/
class SpiUtil {
    companion object {
        fun <T : Any> loadLanguages(kclazz: KClass<T>): Map<String, T> {

            val languageSpi = kclazz.findAnnotation<LanguageSpi>()

            val services = if (languageSpi != null && languageSpi.value.isNotEmpty()) {
                languageSpi.value.stream().map { it.newInstance() }
                    .collect(Collectors.toList())
            } else {
                ServiceLoader.load(kclazz.java, kclazz.java.classLoader).toList()

            }

            val result = mutableMapOf<String, T>()
            services.forEach(Consumer { item ->
                val clazz = item.javaClass
                val annotation =
                    AnnotatedElementUtils.getMergedAnnotation(clazz, ConditionOnLanguage::class.java)
                val language = if (Objects.nonNull(annotation)) {
                    annotation.value.lowercase()
                } else {
                    "uast"
                }
                result[language] = item as T
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
