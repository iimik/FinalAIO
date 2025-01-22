package org.ifinalframework.jetbrains.plugins.aio.api.yapi;


import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.*;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * DefaultYapiService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Service
class DefaultYapiService implements YapiService {
    @Resource
    private YapiProperties properties;
    @Resource
    private YapiClient yapiClient;

    @Override
    public Long getProjectId() {
        final Result<Project> projectInfo = yapiClient.getProjectInfo(properties.getToken());
        if (projectInfo.isSuccess()) {
            return projectInfo.getData().getId();
        }
        return null;
    }

    @Override
    public Long getCatMenuId(String category) {
        // 1. find project id
        final Long projectId = getProjectId();
        if (projectId == null) {
            return null;
        }
        final Result<List<CatMenu>> result = yapiClient.getCatMenus(projectId, properties.getToken());
        if (!result.isSuccess()) {
            return null;
        }
        // 2. find cat id
        return result.getData().stream()
                .filter(it -> it.getName().equals(category))
                .findFirst().map(CatMenu::getId).orElse(null);

    }

    @Override
    public Api getApi(String category, String method, String path) {
        final Long catMenuId = getCatMenuId(category);
        if (catMenuId == null) {
            return null;
        }
        // 3. find api
        final Result<Page<Api>> result = yapiClient.getApiListInCat(properties.getToken(), catMenuId, 1, Integer.MAX_VALUE);
        if (!result.isSuccess()) {
            return null;
        }
        return result.getData().getList().stream()
                .filter(it -> it.getMethod().equalsIgnoreCase(method) && it.getPath().equalsIgnoreCase(path))
                .findFirst()
                .orElse(null);
    }
}
