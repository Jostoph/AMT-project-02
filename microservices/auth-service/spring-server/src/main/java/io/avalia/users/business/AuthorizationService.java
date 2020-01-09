package io.avalia.users.business;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTCreator;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import io.avalia.users.api.model.Token;
import io.avalia.users.api.model.User;
import io.avalia.users.business.models.AuthInfo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {

    @Value("${token.secret}")
    String secret;

    @Override
    public Token generateToken(User user) throws JWTCreationException {
        Token token = new Token();
        JWTCreator.Builder t = JWT.create().withIssuer("auth0")
                .withClaim("email", user.getEmail());

        if(user.getIsAdmin()) {
            t.withClaim("isAdmin", true);
        }

        token.setToken(t.sign(Algorithm.HMAC256(secret)));

        return token;
    }

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
