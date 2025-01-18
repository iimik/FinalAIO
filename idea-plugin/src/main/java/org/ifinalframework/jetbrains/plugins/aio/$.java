package org.ifinalframework.jetbrains.plugins.aio;


import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.application.ReadAction;
import com.intellij.openapi.util.ThrowableComputable;
import com.intellij.util.ThrowableRunnable;
import lombok.SneakyThrows;

/**
 * $
 *
 * @author iimik
 * @since 1.6.0
 **/
public class $ {

    public static final class read {
        public static void run(final ThrowableRunnable runnable) {
            ReadAction.run(runnable);
        }

        @SneakyThrows
        public static <T> T compute(ThrowableComputable<T, Throwable> computable) {
            return ReadAction.compute(computable);
        }
    }

    @Deprecated
    public static void runInReadAction(final ThrowableRunnable runnable) {
        ReadAction.run(runnable);
    }

    @Deprecated
    @SneakyThrows
    public static <T> T computeInReadAction(ThrowableComputable<T, Throwable> computable) {
        return ReadAction.compute(computable);
    }

    public static void async(Runnable runnable) {
        ApplicationManager.getApplication().executeOnPooledThread(runnable);
    }

}
