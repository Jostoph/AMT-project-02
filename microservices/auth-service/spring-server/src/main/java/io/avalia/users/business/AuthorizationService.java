package io.avalia.users.business;

import io.avalia.users.api.model.Token;
import org.springframework.stereotype.Service;

@Service
public class AuthorizationService implements IAuthorizationService {
    @Override
    public Token generateToken() {
        return null;
    }

    @Override
    public boolean checkToken(String token) {
        return false;
    }
}
