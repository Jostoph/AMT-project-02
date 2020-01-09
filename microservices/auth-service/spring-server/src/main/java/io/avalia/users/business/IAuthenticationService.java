package io.avalia.users.business;

public interface IAuthenticationService {
    String hashPassword(String plainTextPassword);
    boolean checkPassword(String plainTextPassword, String hashedPassword);
}
