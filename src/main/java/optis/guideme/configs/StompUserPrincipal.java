package optis.guideme.configs;

import lombok.RequiredArgsConstructor;

import java.security.Principal;

@RequiredArgsConstructor
public class StompUserPrincipal implements Principal {
    private final String name;

    @Override
    public String getName() {
        return name;
    }
}
