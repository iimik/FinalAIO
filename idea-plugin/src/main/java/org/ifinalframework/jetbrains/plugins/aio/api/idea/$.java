package org.ifinalframework.jetbrains.plugins.aio.api.idea;


import com.intellij.openapi.application.ReadAction;
import com.intellij.util.ThrowableRunnable;

/**
 * $
 *
 * @author iimik
 * @since 1.6.0
 **/
public class $ {

    public static void runInReadAction(final ThrowableRunnable runnable) {
        ReadAction.run(runnable);
    }

}
