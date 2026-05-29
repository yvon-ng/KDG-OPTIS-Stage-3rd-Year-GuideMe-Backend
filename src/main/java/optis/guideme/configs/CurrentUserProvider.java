package optis.guideme.configs;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework. security.oauth2.server.resource.authentication.JwtAuthenticationToken;

import java.util.UUID;

public final class CurrentUserProvider {

    private CurrentUserProvider() {
        // prevent instantiation
    }

    public static Jwt getJwt() {
        var authentication = SecurityContextHolder.getContext().getAuthentication();

        if (!(authentication instanceof JwtAuthenticationToken jwtAuth)) {
            throw new IllegalStateException("No JWT authentication found");
        }

        return jwtAuth.getToken();
    }


    public static UUID getUserId() { // get current userid
        return UUID.fromString(getJwt().getClaim("oid"));
    }
}
