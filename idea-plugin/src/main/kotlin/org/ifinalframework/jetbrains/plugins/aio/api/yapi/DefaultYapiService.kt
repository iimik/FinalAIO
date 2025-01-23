package org.ifinalframework.jetbrains.plugins.aio.api.yapi;

import com.itangcent.common.utils.firstOrNull
import jakarta.annotation.Resource
import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.Api
import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.CatMenu
import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.Project
import org.springframework.stereotype.Service


/**
 * DefaultYapiService
 *
 * @author iimik
 * @since 1.6.0
 **/
@Service
class DefaultYapiService : YapiService {
    @Resource
    private lateinit var yapiProperties: YapiProperties

    @Resource
    private lateinit var yapiClient: YapiClient

    override fun getProject(): Project? {
        return yapiClient.getProject(yapiProperties.token!!).data
    }

    override fun getCatMenu(category: String): CatMenu? {
        val project = getProject() ?: return null
        val result = yapiClient.getCatMenus(project.id, yapiProperties.token!!)
        return if (result.isSuccess()) {
            result.data!!.stream()
                .filter { it!!.name == category }
                .firstOrNull()
        } else null
    }

    override fun getApi(category: String, method: String, path: String): Api? {
        val catMenu = getCatMenu(category) ?: return null
        val result = yapiClient.getApiListInCat(yapiProperties.token!!, catMenu.id)
        return if (result.isSuccess()) {
            result.data.list.stream()
                .filter { it.method == method && it.path == path }
                .firstOrNull()
        } else null
    }
}