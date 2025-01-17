package org.ifinalframework.jetbrains.plugins.aio.api.idea.parser;


import com.intellij.psi.PsiClass;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiMethod;
import com.itangcent.common.model.Doc;
import com.itangcent.intellij.logger.Logger;

import javax.inject.Inject;

import java.util.Collections;
import java.util.List;

/**
 * DefaultApiParser
 *
 * @author iimik
 * @since 1.6.0
 **/
public class DefaultApiParser implements ApiParser {

    @Inject
    private Logger logger;

    @Override
    public List<Doc> parse(PsiElement element) {

        if(element instanceof PsiClass){
            final PsiClass psiClass = (PsiClass) element;
            return parse(psiClass, psiClass.getAllMethods());
        }else if(element instanceof PsiMethod){
            final PsiClass psiClass = ((PsiMethod) element).getContainingClass();
            return parse(psiClass, new PsiMethod[]{(PsiMethod) element});
        }

        logger.warn("不支持的PsiElement:" + element);

        return Collections.emptyList();
    }

    private List<Doc> parse(PsiClass psiClass, PsiMethod[] psiMethods) {




        return Collections.emptyList();
    }

}
