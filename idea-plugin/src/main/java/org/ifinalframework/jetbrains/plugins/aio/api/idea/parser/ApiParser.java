package org.ifinalframework.jetbrains.plugins.aio.api.idea.parser;


import com.google.inject.ImplementedBy;
import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.itangcent.common.model.Doc;

import java.util.List;

/**
 * Api解析器
 *
 * @author iimik
 * @since 1.6.0
 **/
@ImplementedBy(DefaultApiParser.class)
public interface ApiParser {

    /**
     * 解析Api文档
     * <ul>
     *     <li>如果是{@link PsiClass}，解析其下所有的{@linkplain PsiMethod 方法}</li>
     *     <li>如果是{@link PsiMethod}，只解析选中的这一个</li>
     * </ul>
     * @return
     */
    List<Doc> parse(PsiElement element);
}
