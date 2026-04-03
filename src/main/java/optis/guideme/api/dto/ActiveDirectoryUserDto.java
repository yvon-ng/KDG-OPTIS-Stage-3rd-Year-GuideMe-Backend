package optis.guideme.api.dto;

import lombok.Getter;

import java.util.List;

@Getter
public class ActiveDirectoryUserDto {
    private String objectId;
    private String mail;
    private String givenName;
    private String surname;
    private List<String> memberOf;

    public ActiveDirectoryUserDto() {}

    public ActiveDirectoryUserDto(
            String objectId,
            String mail,
            String givenName,
            String surname,
            List<String> memberOf) {
        this.objectId = objectId;
        this.mail = mail;
        this.givenName = givenName;
        this.surname = surname;
        this.memberOf = memberOf;
    }
}