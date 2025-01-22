package org.ifinalframework.jetbrains.plugins.aio.api.yapi;


import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.Api;

/**
 * YapiService
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface YapiService {

    Long getProjectId();

    Long getCatMenuId(String category);

    Api getApi(String category, String method, String path);
}
