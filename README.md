# Final AIO

Final AIO(All In One)是一个集成众多热门插件，实现定制化开发的一个IDEA插件

## 插件列表

* 基于[easy-yapi](https://github.com/tangcent/easy-yapi)插件开发，增加定制功能。

## Features

* 支持编写`Markdown`文档并在导出时作为接口描述文件导出。
* 在`Controller`的`Method`的方法上增加`Open Markdown`入口，可快速打开`Markdown`文件
* 在`Controller`的`Method`的方法上增加`Open Api`入口，可快速（在浏览器）打开`Api`文档

---

# easy-yapi

[![CI](https://github.com/tangcent/easy-yapi/actions/workflows/ci.yml/badge.svg)](https://github.com/tangcent/easy-yapi/actions/workflows/ci.yml)
[![codecov](https://codecov.io/gh/tangcent/easy-yapi/branch/master/graph/badge.svg?token=J6RUGI54XV)](https://codecov.io/gh/tangcent/easy-yapi)
[![](https://img.shields.io/jetbrains/plugin/v/12458?color=blue&label=version)](https://plugins.jetbrains.com/plugin/12458-easyyapi)
[![](https://img.shields.io/jetbrains/plugin/d/12458)](https://plugins.jetbrains.com/plugin/12458-easyyapi)
[![Average time to resolve an issue](http://isitmaintained.com/badge/resolution/tangcent/easy-yapi.svg)](http://isitmaintained.com/project/tangcent/easy-yapi "Average time to resolve an issue")
[![Percentage of issues still open](http://isitmaintained.com/badge/open/tangcent/easy-yapi.svg)](http://isitmaintained.com/project/tangcent/easy-yapi "Percentage of issues still open")

## Feature

- [Export API Documents](https://easyyapi.com/documents/use.html)
- [Send API requests](http://easyyapi.com/documents/call.html)

|            | Support                                                                                                                                                                                                                                                                                   | Extended Support                  |
|------------|-------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------|-----------------------------------|
| language   | java, kotlin                                                                                                                                                                                                                                                                              | scala                             |
| web        | [spring](https://spring.io/), [feign](https://spring.io/projects/spring-cloud-openfeign), [jaxrs](https://www.oracle.com/technical-resources/articles/java/jax-rs.html) ([quarkus](https://quarkus.io/) or [jersey](https://eclipse-ee4j.github.io/jersey/))                              | [dubbo](https://dubbo.apache.org) |
| channels   | [Postman](https://easyyapi.com/documents/export2postman.html), [Yapi](https://easyyapi.com/documents/export2yapi.html), [Markdown](https://easyyapi.com/documents/export2markdown.html) , [Curl](https://curl.se/) , [HttpClient](https://plugins.jetbrains.com/plugin/13121-http-client) | -                                 |
| frameworks | javax.validation, Jackson, Gson                                                                                                                                                                                                                                                           | [swagger](https://swagger.io/)    |

## Navigation

* [Guide](https://easyyapi.com/documents/index.html)
* [Installation](https://easyyapi.com/documents/installation.html)
* [Usage](https://easyyapi.com/documents/use.html)
* [Setting](https://easyyapi.com/setting/index.html)
* [Demo](https://easyyapi.com/demo/index.html)

## Run application

- `./gradlew :idea-plugin:runIde` will runs an IDEA instance with the EasyYapi installed.
- `./gradlew clean test` will run all test case.

## Requirements

- IDE: Intellij Idea Ultimate / Intellij Idea Community 2021.2.1 or higher
- JDK: Version 11 or higher

## Compatibility

| JDK | IDE      | status |
|-----|----------|--------|
| 11  | 2021.2.1 | ✓      |
| 15  | 2022.2.3 | ✓      |
| 17  | 2023.1.3 | ✓      |

## Javadoc

- [wiki](https://en.wikipedia.org/wiki/Javadoc)
- [oracle](https://docs.oracle.com/javase/8/docs/technotes/tools/windows/javadoc.html)
- [baike](https://baike.baidu.com/item/javadoc)

## KDoc

- [kotlin-doc](https://kotlinlang.org/docs/reference/kotlin-doc.html)

## Contributing

You can propose a feature request opening an issue or a pull request.

Here is a list of contributors:

<a href="https://github.com/tangcent/easy-yapi/graphs/contributors">
  <img src="https://contrib.rocks/image?repo=tangcent/easy-yapi" />
</a>
