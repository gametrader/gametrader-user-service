package com.gametrader.gametraderuserservice.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import lombok.AllArgsConstructor;;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
@AllArgsConstructor

public class JwtUtils {

    private final Algorithm algorithm;
    private final Long ACCESS_TOKEN_TIME = 2700000L;
    private final Long REFRESH_TOKEN_TIME = 432000000L;
    private final String secret = System.getenv("JWT_ALGORITHM_SECRET");

    public JwtUtils() {
        this.algorithm = Algorithm.HMAC384(secret.getBytes());
    }

    public DecodedJWT decodeJwt(String token) {
        String trimmedToken = token.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(this.algorithm).build();
        return verifier.verify(trimmedToken);
    }


    public String createAccessToken(String username, String issuerUrl, List<?> claims) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME))
                .withIssuer(issuerUrl)
                .withClaim("roles", String.valueOf(claims))
                .sign(this.algorithm);
    }

    public String createRefreshToken(String username, String issuerUrl) {
        return JWT.create().
                withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .withIssuer(issuerUrl)
                .sign(algorithm);
    }
}
