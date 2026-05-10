package optis.guideme.domain.user;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.OrderBy;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import optis.guideme.domain.exception.WrongInputException;

import java.time.LocalDateTime;
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
    private LocalDateTime startDateAtClient;

    @ElementCollection
    @OrderBy("dateTime DESC")
    private List<ClientScore> clientScores = new ArrayList<>();

    public Consultant(UUID userId, String firstName, String lastName, String email, String avatar) {
        super(userId, firstName, lastName, email, avatar);
    }

    @Override
    public Consultant asConsultant() {
        return this;
    }

    public void assignToClient(String clientName, LocalDateTime startDateAtClient) {
        if (this.clientName != null) throw new IllegalStateException("Already assigned to a client");
        this.clientName = clientName;
        this.startDateAtClient = startDateAtClient;
    }

    public void changeClient(String clientName, LocalDateTime startDateAtClient) {
        this.clientName = clientName;
        this.startDateAtClient = startDateAtClient;
    }

    public void addClientScore(ClientScore score) {
        if (clientName == null) throw new WrongInputException("Cannot give Client a score without being assigned to one");
        clientScores.add(score);
    }

    public List<ClientScore> getLatestClientScores(int limit) {
        if (clientScores.isEmpty()) return List.of();
        return clientScores.stream().limit(limit).toList();
    }
}
