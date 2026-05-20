package optis.guideme.application;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class NotificationTemplateService {

    private final MessageSource messageSource;

    public NotificationTemplateService(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    public String render(String key, Object... args) {
        return messageSource.getMessage(key, args, Locale.getDefault());
    }
}
