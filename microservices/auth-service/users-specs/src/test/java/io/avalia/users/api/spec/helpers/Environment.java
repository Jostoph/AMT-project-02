package io.avalia.users.api.spec.helpers;

import cucumber.api.java.en.Given;
import io.avalia.users.ApiException;
import io.avalia.users.ApiResponse;
import io.avalia.users.api.DefaultApi;
import io.avalia.users.api.dto.Credentials;

import java.io.IOException;
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


}
