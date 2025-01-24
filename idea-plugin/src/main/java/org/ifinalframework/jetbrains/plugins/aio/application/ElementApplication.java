package org.ifinalframework.jetbrains.plugins.aio.application;


import com.intellij.openapi.module.Module;
import com.intellij.openapi.module.ModuleUtil;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.ifinalframework.jetbrains.plugins.aio.$;
import org.ifinalframework.jetbrains.plugins.aio.application.aop.AopConfig;
import org.springframework.aop.framework.AopConfigException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * ElementApplication
 *
 * @author iimik
 * @since 0.0.1
 **/
@Slf4j
@RequiredArgsConstructor
public class ElementApplication {
    private final Set<Class<?>> primarySources;
    private final PsiElement element;

    public void run(String... args) {
        final String lang = element.getLanguage().getID().toLowerCase();
        final Module module = ModuleUtil.findModuleForPsiElement(element);
        final Project project = element.getProject();

        $.async(() -> {
            try {
                //fix: 启动报 ClassNotFoundException AopConfigException
                final ClassLoader classLoader = AopConfigException.class.getClassLoader();
                final AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext();
                context.setClassLoader(classLoader);
                System.setProperty("final.language", lang);
                final ElementEnvironment environment = new ElementEnvironment();
                environment.load(classLoader, element);
                context.setEnvironment(environment);
                // bean factory post processor
                context.addBeanFactoryPostProcessor(new ElementApplicationBeanFactoryPostProcessor());
                // element components
                context.registerBean("element", PsiElement.class, () -> element);
                context.registerBean("module", Module.class, () -> module);
                context.registerBean("project", Project.class, () -> project);
                // primarySources
                context.register(primarySources.toArray(new Class<?>[0]));
                // aop
                context.register(AopConfig.class);
                // jackson
                // feign
//                context.register(FeignClientsConfiguration.class);
//                context.register(HttpMessageConvertersAutoConfiguration.class);
//                context.register(FeignAutoConfiguration.class);
                // refresh
                context.refresh();

                // handle
                final List<ElementHandler> elementHandlers = context.getBeanProvider(ElementHandler.class).stream().collect(Collectors.toList());

                if (elementHandlers.isEmpty()) {
                    logger.warn("not found handlers for application: {}", primarySources);
                    return;
                }

                elementHandlers.forEach(handler -> {
                    try {
                        handler.handle(element);
                    } catch (Exception e) {
                        logger.error(e.getMessage(), e);
                    }
                });
            } catch (Exception e) {
                logger.error("处理异常：", e);
            }
        });


    }

    public static void run(Class<?> primaryClass, PsiElement element) {
        new ElementApplication(Set.of(primaryClass), element).run();
    }

}
