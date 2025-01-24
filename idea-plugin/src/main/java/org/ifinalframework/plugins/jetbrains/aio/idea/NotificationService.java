package org.ifinalframework.plugins.jetbrains.aio.idea;


import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationType;
import org.ifinalframework.plugins.jetbrains.aio.application.annotation.EDT;

/**
 * NotificationService
 *
 * @author iimik
 * @since 0.0.1
 **/
public interface NotificationService {
    @EDT
    void notify(NotificationDisplayType displayType, String content, NotificationType notificationType);
}
