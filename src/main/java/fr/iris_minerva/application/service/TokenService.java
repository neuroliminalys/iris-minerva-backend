package fr.iris_minerva.application.service;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.stream.Collectors;

import org.springframework.security.core.Authentication;
import org.springframework.security.oauth2.jwt.JwsHeader;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

@Service
public class TokenService {

    private final JwtEncoder encoder;

    public TokenService(JwtEncoder encoder) {
        this.encoder = encoder;
    }

    public String generateToken(Authentication auth) {
        // crée l'en-tête
        JwsHeader header = JwsHeader.with(() -> "HS256").build();

        String scope = auth.getAuthorities().stream()
                .map((authority) -> authority.getAuthority())
                .collect(Collectors.joining(" ")); // ex: "ROLE_USER ROLE_ADMIN"

        Instant now = Instant.now();
        JwtClaimsSet payload = JwtClaimsSet.builder()
                .issuer("self")
                // a été créé à l'instant
                .issuedAt(now)
                // expire dans une heure
                .expiresAt(now.plus(1, ChronoUnit.HOURS))
                // s'adresse à l'utilisateur connecté : ici renvoie son email
                .subject(auth.getName())
                // scope: correspond aux rôles de l'utilisateur
                .claim("scope", scope)
                .build();

        // la signature sera générée par la méthode encode du JwtEncoder
        return this.encoder.encode(JwtEncoderParameters.from(header, payload)).getTokenValue();
    }
}
