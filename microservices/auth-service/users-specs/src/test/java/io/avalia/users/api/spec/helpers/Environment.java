package io.avalia.users.api.spec.helpers;

import cucumber.api.java.en.Given;
import io.avalia.users.ApiException;
import io.avalia.users.ApiResponse;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.Credentials;
import io.avalia.users.api.dto.Token;
import io.avalia.users.api.dto.User;
import io.avalia.users.api.dto.UserDTO;

import java.io.IOException;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.assertNotNull;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();

    private ApiResponse lastApiResponse;
    private ApiException lastApiException;
    private boolean lastApiCallThrewException;
    private int lastStatusCode;

    private Credentials credentials;
    private Token token;
    private User user;
    private List<UserDTO> users;
    private String password;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.avalia.users.server.url");
        api.getApiClient().setBasePath(url);

    }

    public DefaultApi getApi() {
        return api;
    }

    public void setLastApiResponse(ApiResponse lastApiResponse) {
        this.lastApiResponse = lastApiResponse;
    }

    public void setLastApiException(ApiException lastApiException) {
        this.lastApiException = lastApiException;
    }

    public void setLastApiCallThrewException(boolean lastApiCallThrewException) {
        this.lastApiCallThrewException = lastApiCallThrewException;
    }

    public void setLastStatusCode(int lastStatusCode) {
        this.lastStatusCode = lastStatusCode;
    }

    public void setCredentials(Credentials credentials) {
        this.credentials = credentials;
    }

    public void setToken(Token token) {
        this.token = token;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setUsers(List<UserDTO> users) {
        this.users = users;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public ApiResponse getLastApiResponse() {
        return lastApiResponse;
    }

    public ApiException getLastApiException() {
        return lastApiException;
    }

    public boolean isLastApiCallThrewException() {
        return lastApiCallThrewException;
    }

    public int getLastStatusCode() {
        return lastStatusCode;
    }

    public Credentials getCredentials() {
        return credentials;
    }

    public Token getToken() {
        return token;
    }

    public User getUser() {
        return user;
    }

    public List<UserDTO> getUsers() {
        return users;
    }

    public String getPassword() {
        return password;
    }
}
