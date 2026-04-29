package optis.guideme.domain.user;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DiscriminatorValue("CONSULTANT")
public class Consultant extends User {
    private String clientName;
    private LocalDate startDateAtClient;
    @ElementCollection
    private final List<ClientRating> clientRatings = new ArrayList<>();

    public Consultant(UUID userId, String firstName, String lastName, String email, String avatar) {
        super(userId, firstName, lastName, email, avatar);
    }

    public void assignToClient(String clientName, LocalDate startDateAtClient) {
        if (this.clientName != null) {
            throw new IllegalStateException("Already assigned to a client");
        }
        this.clientName = clientName;
        this.startDateAtClient = startDateAtClient;
    }

    public void changeClient(String clientName, LocalDate startDateAtClient) {
        this.clientName = clientName;
        this.startDateAtClient = startDateAtClient;
    }

    public void rateCurrentClient(ClientRating rating) {
        if (clientName == null) {
            throw new WrongInputException("Cannot rate Client without being assigned to one");
        }
        clientRatings.add(rating);
    }
}
