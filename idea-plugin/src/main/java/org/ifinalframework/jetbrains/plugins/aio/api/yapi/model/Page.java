package org.ifinalframework.jetbrains.plugins.aio.api.yapi.model;


import lombok.Getter;
import lombok.Setter;

import java.util.List;


/**
 * Page
 *
 * @author iimik
 * @since 0.0.1
 **/
@Getter
@Setter
public class Page<T> {
    private Long total;
    private Long count;
    private List<T> list;
}
