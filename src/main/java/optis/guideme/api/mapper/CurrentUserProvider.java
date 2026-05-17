package optis.guideme.api.mapper;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework. security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.stereotype.Component;

import java.util.UUID;

@Component
public class CurrentUserProvider { //TODO static so no inject

    public Jwt getJwt() { //private
        JwtAuthenticationToken authentication = (JwtAuthenticationToken) SecurityContextHolder
                .getContext()
                .getAuthentication();

        return authentication.getToken();
    }

    public UUID getUserId() { //getUser currentuserid
        return UUID.fromString(getJwt().getClaim("oid"));
    }
}
