package optis.guideme.domain.user;


import jakarta.persistence.CascadeType;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Entity
@DiscriminatorValue("CONSULTANT")
@NoArgsConstructor
public class Consultant extends User {
    private String currentClient;
    private LocalDate startDateAtClient;
    @OneToMany
    private List<ClientRating> clientRatings;
    public Consultant(UUID userId, String firstName, String lastName, String email) {
        super(userId, firstName, lastName, email);
    }

    public void rateCurrentClient(ClientRating clientRating) {
        clientRatings.add(clientRating);
    }
}
