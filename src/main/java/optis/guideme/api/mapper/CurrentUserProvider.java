package optis.guideme.api.mapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUserProvider {

    public Jwt getJwt() {
        JwtAuthenticationToken authentication =
                (JwtAuthenticationToken) SecurityContextHolder
                        .getContext()
                        .getAuthentication();

        assert authentication != null;
        return authentication.getToken();
    }

    public UUID getUserId() {
        return UUID.fromString(getJwt().getClaim("oid"));
    }
}
