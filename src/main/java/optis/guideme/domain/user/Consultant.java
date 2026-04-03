package optis.guideme.domain.user;


import java.time.LocalDate;
import java.util.List;

public class Consultant extends User {
    private String currentClient;
    private LocalDate startDateAtClient;
    private List<ClientRating> clientRatings;

    public Consultant(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void rateCurrentClient(ClientRating clientRating) {
        clientRatings.add(clientRating);
    }
}
