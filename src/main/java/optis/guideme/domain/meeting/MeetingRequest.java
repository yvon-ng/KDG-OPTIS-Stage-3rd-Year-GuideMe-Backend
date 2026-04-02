package optis.guideme.domain.meeting;

import optis.guideme.domain.user.User;

import java.time.LocalDateTime;
import java.util.List;

public class MeetingRequest {
    public static final String REFERENCE_CODE_TEMPLATE = "SYNC-%s-Q%d-%s";

    private String referenceCode;
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;
    private int recurrenceIntervalInMonths;
    private Status status;
    private User requestedBy;
    private List<User> requestFor;

    public Meeting accept(){
        return new Meeting();
    }

    public boolean isRecurrent(){
        return recurrenceIntervalInMonths != 0;
    }

    public String generateReferenceCode(){
        // If referenceCode is already generated, return it
        if (this.referenceCode != null && !this.referenceCode.isEmpty()) {
            return this.referenceCode;
        }

        // get all attendees first names, turn it into PascalCase if they have no firstname then take "Unknown"
        String names = requestFor.stream()
                .map(User::getFirstName)
                .map(name -> {
                    if (name == null || name.trim().isEmpty()) {
                        return "Unknown";
                    }
                    name = name.trim();
                    return name.length() == 1
                            ? name.toUpperCase()
                            : name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
                })
                .reduce("", String::concat);

        // get Q ...
        int month = startDateTime.getMonthValue();
        int quarter = (month - 1) / 3 + 1;

        // Generate a 5-digit random number (00000-99999)
        int randomNumber = (int)(Math.random() * 100000);
        String formattedRandomNumber = String.format("%05d", randomNumber); // always 5 digits

        this.referenceCode = String.format(
                REFERENCE_CODE_TEMPLATE,
                names,
                quarter,
                formattedRandomNumber
        );

        return this.referenceCode;
    }

}
