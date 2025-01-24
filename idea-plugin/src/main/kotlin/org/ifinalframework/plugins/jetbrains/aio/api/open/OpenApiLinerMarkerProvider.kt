package org.ifinalframework.plugins.jetbrains.aio.api.open;

import com.intellij.codeInsight.daemon.GutterIconNavigationHandler
import com.intellij.codeInsight.daemon.LineMarkerInfo
import com.intellij.codeInsight.daemon.LineMarkerProvider
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder
import com.intellij.psi.PsiElement
import org.ifinalframework.plugins.jetbrains.aio.Icons
import org.ifinalframework.plugins.jetbrains.aio.util.ElementKt.isMethodLine


/**
 * OpenApiLinerMarkerProvider
 * @issue 10
 * @author iimik
 * @since 0.0.1
 **/
class OpenApiLinerMarkerProvider : LineMarkerProvider {
    override fun getLineMarkerInfo(element: PsiElement): LineMarkerInfo<*>? {
        if (!element.isMethodLine()) {
            return null;
        }

        val builder = NavigationGutterIconBuilder.create(Icons.API)
        builder.setTargets(element)
        builder.setTooltipText("Open API")
        return builder.createLineMarkerInfo(element, GutterIconNavigationHandler { e, elt ->
            TODO("打开对应的URL")
        })

    }
}