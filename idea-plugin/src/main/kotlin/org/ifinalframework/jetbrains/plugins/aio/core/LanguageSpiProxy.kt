package org.ifinalframework.jetbrains.plugins.aio.core;

import com.intellij.psi.PsiElement
import com.itangcent.common.utils.stream
import java.lang.reflect.InvocationHandler
import java.lang.reflect.Method
import java.util.*


/**
 * LanguageProxy
 *
 * @author iimik
 * @since 0.0.1
 **/
class LanguageSpiProxy : InvocationHandler {
    private val services: Map<String, Any>

    constructor(services: Map<String, Any>) {
        this.services = services
    }

    override fun invoke(proxy: Any?, method: Method?, args: Array<out Any>?): Any? {
        val element = args.stream().filter { it is PsiElement }.findFirst().orElse(null) as PsiElement
        if (Objects.isNull(element)) {
            throw IllegalArgumentException("element must not be null")
        }

        val language = element.language.id.lowercase()

        val service = services[language] ?: return null;
        return method?.invoke(service, *args!!)

    }
}