package io.avalia.users.business.models;

public class AuthInfo {

    private String email;
    private boolean isAdmin;

    public AuthInfo(String email, boolean isAdmin) {
        this.email = email;
        this.isAdmin = isAdmin;
    }

    public String getEmail() {
        return email;
    }

    public boolean isAdmin() {
        return isAdmin;
    }
}
