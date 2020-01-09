package io.avalia.users.business;

import io.avalia.users.api.model.Token;

public interface IAuthorizationService {
    Token generateToken();
    boolean checkToken(String token);
}
