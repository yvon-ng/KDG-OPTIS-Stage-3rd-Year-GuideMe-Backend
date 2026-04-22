package optis.guideme.domain.meetingRequest;

import optis.guideme.domain.user.User;
import org.springframework.stereotype.Component;

import java.security.SecureRandom;

@Component
public class DefaultReferenceCodeGenerator { // !!!!!!!!  use in service and check in db if code already exist
    public static final String REFERENCE_CODE_TEMPLATE = "%s-Q%d-%02d-%s";
    private static final SecureRandom RANDOM = new SecureRandom();

    public String generateReferenceCode(MeetingRequest request) {
        // get all attendees first names, turn it into PascalCase if they have no firstname then take "Unknown"
        String names = request.getRequestFor().stream()
                .map(User::getFirstName)
                .map(name -> {
                    if (name == null || name.trim().isEmpty()) {
                        return "Unknown";
                    }

                    name = name.trim();

                    return name.length() == 1
                            ? name.toUpperCase()
                            : name.substring(0, 1).toUpperCase()
                            + name.substring(1).toLowerCase();
                })
                .reduce("", String::concat);

        // get Q ...
        int month = request.getStartDateTime().getMonthValue();
        int quarter = (month - 1) / 3 + 1;

        // get last 2 digits of year
        int year = request.getStartDateTime().getYear() % 100;

        // generate unique_code
        int random = RANDOM.nextInt((int) Math.pow(36, 4));
        String randomCode = String.format("%4s", Integer.toString(random, 36).toUpperCase()).replace(' ', '0');

        return String.format(
                REFERENCE_CODE_TEMPLATE,
                names,
                quarter,
                year,
                randomCode
        );
    }
}
