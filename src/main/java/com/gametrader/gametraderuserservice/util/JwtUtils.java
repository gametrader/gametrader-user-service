package com.gametrader.gametraderuserservice.util;


import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

@Component
public class JwtUtils {

    private final Algorithm algorithm;


    public JwtUtils() {
        this.algorithm = Algorithm.HMAC384("secret".getBytes());
    }

    public DecodedJWT decodeJwt(String token) {
        String trimmedToken = token.substring("Bearer ".length());
        JWTVerifier verifier = JWT.require(this.algorithm).build();
        return verifier.verify(trimmedToken);
    }


    public String createAccessToken(String username, String issuerUrl, List<?> claims) {
        return JWT.create()
                .withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 2700000))
                .withIssuer(issuerUrl)
                .withClaim("roles", String.valueOf(claims))
                .sign(this.algorithm);
    }

    public String createRefreshToken(String username, String issuerUrl) {
        return JWT.create().
                withSubject(username)
                .withExpiresAt(new Date(System.currentTimeMillis() + 432000000))
                .withIssuer(issuerUrl)
                .sign(algorithm);
    }
}
