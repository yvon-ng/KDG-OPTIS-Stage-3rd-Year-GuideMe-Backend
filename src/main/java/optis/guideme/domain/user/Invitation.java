package optis.guideme.domain.user;


import java.util.UUID;

public class Invitation {
    // user reaccept must be done in service layer
    private UUID userId;
    private String email;
    private Role role;
    private String inviteCode;

    public Invitation(UUID userId, String email, Role role) {
        this.userId = userId;
        this.email = email;
        this.role = role;
        //todo generate invitation code
    }
}
