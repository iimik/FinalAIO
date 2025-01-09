package com.itangcent.idea.plugin.api.export.yapi

import com.google.inject.Inject
import com.intellij.notification.Notification
import com.intellij.notification.NotificationType
import com.intellij.notification.Notifications
import com.intellij.psi.PsiElement
import com.intellij.util.containers.ContainerUtil
import com.itangcent.common.model.Doc
import com.itangcent.common.utils.notNullOrBlank
import com.itangcent.idea.plugin.api.ClassApiExporterHelper
import com.itangcent.idea.plugin.api.export.core.Folder
import java.util.*
import java.util.concurrent.ConcurrentHashMap
import kotlin.collections.set


class YapiApiExporter : AbstractYapiApiExporter() {

    @Inject
    private lateinit var classApiExporterHelper: ClassApiExporterHelper

    fun export() {
        val serverFound = yapiSettingsHelper.getServer(false).notNullOrBlank()
        if (serverFound) {
            doExport()
        }
    }

    fun export(element: PsiElement):List<Doc> {

        val docs: MutableList<Doc> = Collections.synchronizedList(ArrayList())
        classExporter!!.export(
            element,
            docHandle = docs::add
        )
        return docs;

    }

    private fun doExport() {

        val docs = classApiExporterHelper.export()
        docs.forEach { doc ->exportDoc(doc)}
        Notifications.Bus.notify(Notification("Final Api Group", "导出到Yapi接口:${docs.size}个", NotificationType.INFORMATION), project)
//        var anyFound = false
//        classApiExporterHelper.export {
//            anyFound = true
//            exportDoc(it)
//        }
//        if (anyFound) {
//            logger.info("Apis exported completed")
//        } else {
//            logger.info("No api be found to export!")
//        }
    }

    //privateToken+folderName -> CartInfo
    private val folderNameCartMap: ConcurrentHashMap<String, CartInfo> = ConcurrentHashMap()

    @Synchronized
    override fun getCartForFolder(folder: Folder, privateToken: String): CartInfo? {
        var cartInfo = folderNameCartMap["$privateToken${folder.name}"]
        if (cartInfo != null) return cartInfo

        cartInfo = super.getCartForFolder(folder, privateToken)
        if (cartInfo != null) {
            folderNameCartMap["$privateToken${folder.name}"] = cartInfo
        }
        return cartInfo
    }

    private var successExportedCarts: MutableSet<String> = ContainerUtil.newConcurrentSet()

    override fun exportDoc(doc: Doc, privateToken: String, cartId: String): Boolean {
        if (super.exportDoc(doc, privateToken, cartId)) {
            if (successExportedCarts.add(cartId)) {
                logger.info(
                    "Export to ${
                        yapiApiHelper.getCartWeb(
                            yapiApiHelper.getProjectIdByToken(privateToken)!!,
                            cartId
                        )
                    } success"
                )
            }
            return true
        }
        return false
    }
}