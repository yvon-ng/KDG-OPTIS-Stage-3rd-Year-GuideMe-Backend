package optis.guideme.configs;

import lombok.RequiredArgsConstructor;
import optis.guideme.application.UserService;
import optis.guideme.domain.user.Role;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationToken;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.UUID;

import static optis.guideme.configs.SecurityConfig.AZURE_MANAGER_ROLE;

@Component
@RequiredArgsConstructor
public class CustomJwtAuthenticationConverter implements Converter<Jwt, AbstractAuthenticationToken> {
    private final UserService userService;

    @Override
    public AbstractAuthenticationToken convert(Jwt jwt) {
        String email = jwt.getClaim("email");
        UUID oid = UUID.fromString(jwt.getClaim("oid"));
        List<String> roles = jwt.getClaimAsStringList("roles");
        if (roles == null) roles = List.of();
        Role role = roles.contains(AZURE_MANAGER_ROLE) ? Role.MANAGER : Role.CONSULTANT;
        userService.getOrCreateUser(oid, email, role);
        JwtGrantedAuthoritiesConverter authoritiesConverter = new JwtGrantedAuthoritiesConverter();

        authoritiesConverter.setAuthoritiesClaimName("roles");
        authoritiesConverter.setAuthorityPrefix("ROLE_");
        Collection<GrantedAuthority> authorities = authoritiesConverter.convert(jwt);

        return new JwtAuthenticationToken(jwt, authorities);
    }
}