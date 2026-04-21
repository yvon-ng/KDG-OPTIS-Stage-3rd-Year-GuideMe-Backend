package optis.guideme.domain.user;


import lombok.Getter;
import optis.guideme.domain.exception.WrongInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Consultant extends User {
    private Client client; // nullable
    private List<ClientRating> clientRatings = new ArrayList<>();

    public Consultant(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void assignToClient(String client, LocalDate startDate) {
        if (this.client != null) {
            throw new IllegalStateException("Already assigned to a client");
        }
        this.client = new Client(client, startDate);
    }

    public void changeClient(String client, LocalDate startDate) {
        this.client = new Client(client, startDate);
    }

    public void rateCurrentClient(ClientRating rating) {
        if (client == null) {
            throw new WrongInputException("Cannot rate Client without being assigned to one");
        }
        clientRatings.add(rating);
    }
}
