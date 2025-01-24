package org.ifinalframework.plugins.jetbrains.aio;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.application.WriteAction;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.psi.PsiElement;
import com.intellij.util.ThrowableRunnable;
import lombok.SneakyThrows;
import org.ifinalframework.plugins.jetbrains.aio.application.ElementApplication;

/**
 * $
 *
 * @author iimik
 * @see <a href="https://plugins.jetbrains.com/docs/intellij/threading-model.html#read-write-lock>Threading Model</a>
 * @since 0.0.1
 **/
public class $ {

    public static final class read {
        @SneakyThrows
        public static void run(ThrowableRunnable<Throwable> runnable) {

            ReadAction.run(runnable);
        }

        @SneakyThrows
        public static <T> T compute(ThrowableComputable<T, Throwable> computable) {
            return ReadAction.compute(computable);
        }
    }

    public static final class write {
        @SneakyThrows
        public static void run(ThrowableRunnable<Throwable> runnable) {
            WriteAction.run(runnable);
        }

        @SneakyThrows
        public static <T> T compute(ThrowableComputable<T, Throwable> computable) {
            return WriteAction.compute(computable);
        }
    }

    public static void dispatch(Runnable runnable) {
        ApplicationManager.getApplication().invokeLater(runnable);
    }

    public static void async(Runnable runnable) {
        ApplicationManager.getApplication().executeOnPooledThread(runnable);
    }

    public static void run(Class<?> application, PsiElement element) {
        ElementApplication.run(application, element);
    }

}
