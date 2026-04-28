package optis.guideme.domain.user;


//@AllArgsConstructor
public class Invitation {
    // user reaccept must be done in service layer
    private String email;
    private Role role;
    private String inviteCode;

    public Invitation(String email, Role role) {
        this.email = email;
        this.role = role;
        //todo generate invitation code
    }
}
