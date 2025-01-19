package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.psi.PsiElement;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.idea.aop.IdeaThreadAdvisor;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * DefaultElementApplicationRunner
 *
 * @author iimik
 * @since 0.0.1
 **/
public class DefaultElementApplicationRunner implements ElementApplicationRunner {
    @Override
    public void run(ElementApplication application, PsiElement element) {
        $.async(() -> {
            //fix: 启动报 ClassNotFoundException AopConfigException
            final ClassLoader classLoader = AopConfigException.class.getClassLoader();
            final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
            context.setClassLoader(classLoader);
            final ElementEnvironment environment = new ElementEnvironment();
            environment.load(classLoader, element);
            context.setEnvironment(environment);
            context.register(IdeaThreadAdvisor.class);
            application.init(context);
            context.refresh();
            application.onEvent(context, element);
        });
    }
}
