package org.ifinalframework.jetbrains.plugins.aio.idea;


import com.intellij.notification.Notification;
import com.intellij.notification.NotificationDisplayType;
import com.intellij.notification.NotificationType;
import com.intellij.notification.Notifications;
import org.springframework.stereotype.Component;

/**
 * DefaultNotificationService
 *
 * @author iimik
 * @since 0.0.1
 **/
@Component
public class DefaultNotificationService implements NotificationService{

    private static final String NOTIFICATION_GROUP_PREFIX = "FINAL_AIO_";

    @Override
    public void notify(NotificationDisplayType displayType, String content, NotificationType notificationType) {
        final Notification notification = new Notification(NOTIFICATION_GROUP_PREFIX + displayType, content, notificationType);
        Notifications.Bus.notify(notification);
    }
}
