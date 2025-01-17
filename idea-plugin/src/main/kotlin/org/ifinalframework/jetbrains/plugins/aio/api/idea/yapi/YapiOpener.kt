package org.ifinalframework.jetbrains.plugins.aio.api.idea.yapi;

import com.google.inject.Inject
import com.google.inject.Singleton
import com.intellij.psi.PsiClass
import com.intellij.psi.PsiMethod
import com.itangcent.idea.plugin.api.ClassApiExporterHelper
import com.itangcent.idea.plugin.api.export.yapi.YapiApiHelper
import com.itangcent.idea.plugin.api.export.yapi.YapiFormatter
import com.itangcent.idea.plugin.api.export.yapi.findExistApi
import com.itangcent.idea.plugin.settings.helper.YapiSettingsHelper
import com.itangcent.idea.utils.ModuleHelper
import com.itangcent.intellij.context.ActionContext
import org.ifinalframework.jetbrains.plugins.aio.api.idea.core.ApiOpener
import java.awt.Desktop
import java.io.IOException
import java.net.URI

/**
 * 快速打开Yapi云文档
 *
 * ##### URL格式
 *
 * ```
 * ${server}/project/${projectId}/interface/api/${_id}
 * ```
 *
 * @author iimik
 * @since 0.0.1
 **/
@Singleton
class YapiOpener : ApiOpener {
    @Inject
    protected lateinit var yapiApiHelper: YapiApiHelper

    @Inject
    protected lateinit var yapiSettingsHelper: YapiSettingsHelper

    @Inject
    private lateinit var actionContext: ActionContext

    @Inject
    private lateinit var moduleHelper: ModuleHelper

    @Inject
    private lateinit var classApiExporterHelper: ClassApiExporterHelper

    @Inject
    private lateinit var yapiFormatter: YapiFormatter

    override fun open(method: PsiMethod) {

        val psiClass = actionContext.callInReadUI { method.containingClass } as PsiClass
        val module = actionContext.callInReadUI { moduleHelper.findModule(psiClass) }
        val token = yapiSettingsHelper.getPrivateToken(module!!)
        val docs = classApiExporterHelper.export()

        if (docs.isEmpty()) return

        val projectId = yapiApiHelper.getProjectIdByToken(token!!)

        docs.forEach { doc ->
            val items = yapiFormatter.doc2Items(doc)
            items.forEach { item ->
                yapiApiHelper.findExistApi(token, item["path"] as String, item["method"] as String)?.let {
                    // server/project/96/interface/api/168955
                    val url = "${yapiSettingsHelper.getServer()}/project/${projectId}/interface/api/${it["_id"]}"
                    open(url)
                }

            }
        }


    }

    /**
     * 在浏览器器中打开url
     */
    private fun open(url: String) {
        try {

            if (Desktop.isDesktopSupported()) {
                Desktop.getDesktop().browse(URI(url))
            } else {
                Runtime.getRuntime().exec("open '$url'")
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
    }
}