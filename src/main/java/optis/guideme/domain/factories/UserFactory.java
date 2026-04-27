package optis.guideme.domain.factories;

import optis.guideme.api.dto.ActiveDirectoryUserDto;
import optis.guideme.domain.user.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class UserFactory {

    public User addNewUser(ActiveDirectoryUserDto adUser) {
        validateAdUser(adUser);

        Role role = determineRoleFromAdGroups(adUser.getMemberOf());

        return switch (role) {
            case MANAGER -> new Manager(UUID.randomUUID(),adUser.getGivenName(), adUser.getSurname(), adUser.getMail());
            case CONSULTANT -> new Consultant(UUID.randomUUID(),adUser.getGivenName(), adUser.getSurname(), adUser.getMail());
        };
    }

    private Role determineRoleFromAdGroups(List<String> adGroups) {
        if (adGroups == null || adGroups.isEmpty()) {
            return Role.CONSULTANT; // default
        }

        if (adGroups.contains("CN=Meeting-Managers,OU=Groups")) {
            return Role.MANAGER;
        }
        if (adGroups.contains("CN=Consultants,OU=Groups")) {
            return Role.CONSULTANT;
        }

        return Role.CONSULTANT;
    }

    private void validateAdUser(ActiveDirectoryUserDto adUser) {
        if (adUser == null) {
            throw new IllegalArgumentException("AD user cannot be null");
        }
        if (adUser.getMail() == null || adUser.getMail().isBlank()) {
            throw new IllegalArgumentException("AD user must have email");
        }
        if (adUser.getGivenName() == null || adUser.getGivenName().isBlank()) {
            throw new IllegalArgumentException("AD user must have given name");
        }
        if (adUser.getSurname() == null || adUser.getSurname().isBlank()) {
            throw new IllegalArgumentException("AD user must have surname");
        }
    }
}
