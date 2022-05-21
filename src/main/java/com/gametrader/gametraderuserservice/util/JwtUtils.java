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
    private static final int ACCESS_TOKEN_TIME = 2700000;
    private static final int REFRESH_TOKEN_TIME = 432000000;

    private final static String SECRET = System.getenv("SECRET");

    public JwtUtils() {
        this.algorithm = Algorithm.HMAC384(SECRET.getBytes());
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
