package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.psi.PsiElement;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigRegistry;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

/**
 * IElementApplication
 *
 * @author iimik
 * @since 0.0.1
 **/
@EnableAspectJAutoProxy
public interface ElementApplication {

    void init(AnnotationConfigRegistry register);

    void onEvent(ApplicationContext context, PsiElement element);

}
