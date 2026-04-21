package optis.guideme.domain.user;


import lombok.Getter;
import optis.guideme.domain.exception.WrongInputException;
import optis.guideme.domain.user.client.Client;
import optis.guideme.domain.user.client.ClientAssignment;
import optis.guideme.domain.user.client.ClientRating;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
public class Consultant extends User {
    private ClientAssignment clientAssignment; // nullable
    private List<ClientRating> clientRatings = new ArrayList<>();

    public Consultant(String firstName, String lastName, String email) {
        super(firstName, lastName, email);
    }

    public void assignToClient(Client client, LocalDate startDate) {
        if (this.clientAssignment != null) {
            throw new IllegalStateException("Already assigned to a client");
        }
        this.clientAssignment = new ClientAssignment(client, startDate);
    }

    public void changeClient(Client client, LocalDate startDate) {
        this.clientAssignment = new ClientAssignment(client, startDate);
    }

    public void rateCurrentClient(ClientRating rating) {
        if (clientAssignment == null) {
            throw new WrongInputException("Cannot rate Client without being assigned to one");
        }
        clientRatings.add(rating);
    }
}
