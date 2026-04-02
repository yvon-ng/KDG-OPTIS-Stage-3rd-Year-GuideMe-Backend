package optis.guideme.domain;


import java.time.LocalDate;

public class Consultant extends User {
    private String currentClient;
    private LocalDate startDateAtClient;
    private Rating currentClientRating;

    public Consultant(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void rateCurrentClient(Rating rating) {
        this.currentClientRating = rating;
    }
}
