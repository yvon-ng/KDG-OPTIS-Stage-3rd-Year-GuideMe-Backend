package optis.guideme.domain.factories;

import optis.guideme.api.dto.ActiveDirectoryUserDto;
import optis.guideme.domain.user.Consultant;
import optis.guideme.domain.user.Manager;
import optis.guideme.domain.user.Role;
import optis.guideme.domain.user.User;

import java.util.List;

public class UserFactory {

    public User addNewUser(ActiveDirectoryUserDto adUser) {
        validateAdUser(adUser);

        Role role = determineRoleFromAdGroups(adUser.getMemberOf());

        return switch (role) {
            case MANAGER -> new Manager(adUser.getGivenName(), adUser.getSurname(), adUser.getMail());
            case CONSULTANT -> new Consultant(adUser.getGivenName(), adUser.getSurname(), adUser.getMail());
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
