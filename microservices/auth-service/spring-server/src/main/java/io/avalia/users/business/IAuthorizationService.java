package io.avalia.users.business;

import com.auth0.jwt.exceptions.JWTCreationException;
import io.avalia.users.api.model.Token;
import io.avalia.users.api.model.User;
import io.avalia.users.business.models.AuthInfo;

public interface IAuthorizationService {
    Token generateToken(User user) throws JWTCreationException;
    AuthInfo decodeToken(String token);
}
