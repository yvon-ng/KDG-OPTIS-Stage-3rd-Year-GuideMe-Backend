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

//    public String getObjectId() { return objectId; }
//    public String getMail() { return mail; }
//    public String getGivenName() { return givenName; }
//    public String getSurname() { return surname; }
//    public List<String> getMemberOf() { return memberOf; }

//    // Setters for deserialization
//    public void setObjectId(String objectId) { this.objectId = objectId; }
//    public void setMail(String mail) { this.mail = mail; }
//    public void setGivenName(String givenName) { this.givenName = givenName; }
//    public void setSurname(String surname) { this.surname = surname; }
//    public void setMemberOf(List<String> memberOf) { this.memberOf = memberOf; }
}