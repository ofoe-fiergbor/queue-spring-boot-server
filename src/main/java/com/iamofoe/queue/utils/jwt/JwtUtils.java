package com.iamofoe.queue.utils.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtUtils {
    private static final String SECRETE_KEY= "super_secrete_key";
    private static final String SUBJECT = "User details";
    private static final String ISSUER = "queue";
    private static final String CLAIM = "phoneNumber";

    public String createAccessToken(String phoneNumber) {
        return JWT.create().withSubject(SUBJECT)
                .withClaim(CLAIM, phoneNumber)
                .withIssuedAt(new Date())
                .withIssuer(ISSUER)
                .sign(Algorithm.HMAC256(SECRETE_KEY.getBytes()));
    }

    public String validateToken(String token) throws JWTVerificationException {
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256(SECRETE_KEY.getBytes()))
                .withSubject(SUBJECT)
                .withIssuer(ISSUER).build();
        DecodedJWT decodedJWT = jwtVerifier.verify(token);
        return decodedJWT.getClaim(CLAIM).asString();
    }

}
