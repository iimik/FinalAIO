package org.ifinalframework.plugins.jetbrains.aio.service

import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.core.annotation.LanguageSpi
import org.ifinalframework.plugins.jetbrains.aio.service.java.JavaElementService
import org.ifinalframework.plugins.jetbrains.aio.service.kotlin.KotlinElementService


/**
 * ElementService
 *
 * @author iimik
 * @since 0.0.1
 **/
@LanguageSpi(
    JavaElementService::class,
    KotlinElementService::class,
)
interface ElementService {
    fun getMethodElement(element: PsiElement): PsiElement?

    fun hasAnyAnnotation(element: PsiElement, anns: Collection<String>): Boolean?

}