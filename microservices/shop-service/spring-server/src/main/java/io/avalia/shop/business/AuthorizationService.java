package io.avalia.shop.business;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.avalia.shop.business.models.AuthInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {

    @Value("${token.secret}")
    String secret;

    @Override
    public AuthInfo decodeToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(Algorithm.HMAC256(secret))
                    .withIssuer("auth0").build();
            DecodedJWT jwt = verifier.verify(token);

            return new AuthInfo(jwt.getClaim("email").asString(), !jwt.getClaim("isAdmin").isNull());
        } catch (JWTVerificationException e) {
            return null;
        }
    }
}
