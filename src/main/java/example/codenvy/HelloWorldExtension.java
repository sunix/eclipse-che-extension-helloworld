package example.codenvy;
import com.codenvy.ide.api.extension.Extension;
import com.codenvy.ide.api.notification.Notification;
import com.codenvy.ide.api.notification.Notification.Type;
import com.codenvy.ide.api.notification.NotificationManager;
import com.google.inject.Inject;

@Extension(title = "Hello world", version = "1.0.0")
public class HelloWorldExtension {
    @Inject
    public HelloWorldExtension(NotificationManager notificationManager) {
        Notification notification = new Notification("Hello World", Type.INFO);
        notificationManager.showNotification(notification);
    }
}

