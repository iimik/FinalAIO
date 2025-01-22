package org.ifinalframework.jetbrains.plugins.aio.api.yapi;


import org.ifinalframework.jetbrains.plugins.aio.api.yapi.model.*;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * YapiClient
 *
 * @author iimik
 * @see <a bref="https://hellosean1025.github.io/yapi/openapi.html">yapi开放 api</a>
 * @since 0.0.1
 **/
@FeignClient(name = "yapi-client", url = "${final.api.yapi.server-url}")
public interface YapiClient {
    /**
     * 获取项目基本信息
     *
     * @param token 项目token
     * @return
     */
    @GetMapping("/api/project/get")
    Result<Project> getProjectInfo(@RequestParam("token") String token);

    /**
     * 获取项目分类菜单列表
     *
     * @param projectId 项目ID
     * @param token     项目token
     * @return
     */
    @GetMapping("/api/interface/getCatMenu")
    Result<List<CatMenu>> getCatMenus(@RequestParam("project_id") Long projectId, @RequestParam("token") String token);

    /**
     * 获取某个分类下接口列表
     *
     * @param token 项目token
     * @param catId 分类id
     * @param page  当前页面
     * @param limit 每页数量，默认为10，如果不想要分页数据，可将 limit 设置为比较大的数字，比如 1000
     * @return
     */
    @GetMapping("/api/interface/list_cat")
    Result<Page<Api>> getApiListInCat(@RequestParam("token") String token, @RequestParam("catid") Long catId, @RequestParam("page") Integer page, @RequestParam("limit") Integer limit);
}
