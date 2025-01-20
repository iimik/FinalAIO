package org.ifinalframework.jetbrains.plugins.aio;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.psi.PsiElement;
import com.intellij.util.ThrowableRunnable;
import lombok.SneakyThrows;
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

    public static final class write {
        @SneakyThrows
        public static void run(ThrowableRunnable<Throwable> computable) {
            WriteAction.run(computable);
        }
    }

    public static void async(Runnable runnable) {
        ApplicationManager.getApplication().executeOnPooledThread(runnable);
    }

    public static void run(Class<?> application, PsiElement element) {
        ElementApplication.run(application, element);
    }

}
