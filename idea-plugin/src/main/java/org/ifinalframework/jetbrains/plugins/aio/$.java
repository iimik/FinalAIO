package org.ifinalframework.jetbrains.plugins.aio;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.psi.PsiElement;
import lombok.SneakyThrows;
import org.ifinalframework.jetbrains.plugins.aio.application.DefaultElementApplicationRunner;
import org.ifinalframework.jetbrains.plugins.aio.application.ElementApplication;

/**
 * $
 *
 * @author iimik
 * @since 0.0.1
 **/
public class $ {

    public static final class read {
        @SneakyThrows
        public static <T> T compute(ThrowableComputable<T, Throwable> computable) {
            return ReadAction.compute(computable);
        }
    }

    public static void async(Runnable runnable) {
        ApplicationManager.getApplication().executeOnPooledThread(runnable);
    }

    public static void run(ElementApplication application, PsiElement element) {
        new DefaultElementApplicationRunner().run(application, element);
    }

}
