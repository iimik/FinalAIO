package com.itangcent.common.model

import com.itangcent.common.utils.SimpleExtensible
import java.io.Serializable

/**
 * Instances of the [Doc] represent a document(Http or RPC) in the project.
 */
open class Doc : SimpleExtensible(), Serializable {

    /**
     * The element associated the origin code.
     */
    var resource: Any? = null

    /**
     * 分类
     */
    var category: String? = null;

    /**
     * 名称
     * Returns the name of the doc.
     */
    var name: String? = null

    /**
     * Markdown
     * Returns the markdown of the doc.
     */
    var markdown: String? = null

    /**
     * 描述
     * Returns the description of the doc.
     * Explain what this document represented in a human readable way.
     */
    var desc: String? = null
}