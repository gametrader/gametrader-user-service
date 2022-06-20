package com.gametrader.gametraderuserservice.util;

import java.util.ArrayList;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ContextConfiguration(classes = {JwtUtils.class})
@ExtendWith(SpringExtension.class)
class JwtUtilsTest {
    @Autowired
    private JwtUtils jwtUtils;

    /**
     * Method under test: {@link JwtUtils#decodeJwt(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDecodeJwt() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   java.lang.StringIndexOutOfBoundsException: begin 7, end 6, length 6
        //       at java.lang.String.checkBoundsBeginEnd(String.java:4604)
        //       at java.lang.String.substring(String.java:2707)
        //       at java.lang.String.substring(String.java:2680)
        //       at com.gametrader.gametraderuserservice.util.JwtUtils.decodeJwt(JwtUtils.java:26)
        //   In order to prevent decodeJwt(String)
        //   from throwing StringIndexOutOfBoundsException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   decodeJwt(String).
        //   See https://diff.blue/R013 to resolve this issue.

        this.jwtUtils.decodeJwt("ABC123");
    }

    /**
     * Method under test: {@link JwtUtils#decodeJwt(String)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testDecodeJwt2() {
        // TODO: Complete this test.
        //   Reason: R013 No inputs found that don't throw a trivial exception.
        //   Diffblue Cover tried to run the arrange/act section, but the method under
        //   test threw
        //   com.auth0.jwt.exceptions.JWTDecodeException: The token was expected to have 3 parts, but got 1.
        //       at com.auth0.jwt.TokenUtils.splitToken(TokenUtils.java:21)
        //       at com.auth0.jwt.JWTDecoder.<init>(JWTDecoder.java:27)
        //       at com.auth0.jwt.JWT.decode(JWT.java:21)
        //       at com.auth0.jwt.JWTVerifier.verify(JWTVerifier.java:352)
        //       at com.gametrader.gametraderuserservice.util.JwtUtils.decodeJwt(JwtUtils.java:28)
        //   In order to prevent decodeJwt(String)
        //   from throwing JWTDecodeException, add constructors or factory
        //   methods that make it easier to construct fully initialized objects used in
        //   decodeJwt(String).
        //   See https://diff.blue/R013 to resolve this issue.

        this.jwtUtils.decodeJwt("Bearer ");
    }

    /**
     * Method under test: {@link JwtUtils#createAccessToken(String, String, java.util.List)}
     */
    @Test
    @Disabled("TODO: Complete this test")
    void testCreateAccessToken() {
        // TODO: Complete this test.
        //   Reason: R031 Method may be time-sensitive.
        //   Diffblue Cover was only able to write tests which were time-sensitive.
        //   The assertions no longer passed when run at an alternate date, time and
        //   timezone. Try refactoring the method to take a java.util.Clock instance so
        //   that the time can be parameterized during testing.
        //   Please see https://diff.blue/R031

        this.jwtUtils.createAccessToken("janedoe", "https://example.org/example", new ArrayList<>());
    }
}

