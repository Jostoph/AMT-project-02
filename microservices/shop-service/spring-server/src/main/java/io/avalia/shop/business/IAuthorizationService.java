package io.avalia.shop.business;
import io.avalia.shop.business.models.AuthInfo;

public interface IAuthorizationService {
    AuthInfo decodeToken(String token);
}
