package optis.guideme.domain.user;


import java.time.LocalDate;

public class Consultant extends User {
    private String currentClient;
    private LocalDate startDateAtClient;
    private Rating currentClientRating;
    private Rating previousClientRating; //todo navragen

    public Consultant(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void rateCurrentClient(Rating rating) {
        this.currentClientRating = rating;
    }
}
