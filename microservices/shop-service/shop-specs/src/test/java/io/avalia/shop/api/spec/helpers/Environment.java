package io.avalia.shop.api.spec.helpers;

import io.avalia.users.api.DefaultApi;
import jdk.nashorn.internal.parser.Token;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by Olivier Liechti on 24/06/17.
 */
public class Environment {

    private DefaultApi api = new DefaultApi();
    private Token token;

    public Environment() throws IOException {
        Properties properties = new Properties();
        properties.load(this.getClass().getClassLoader().getResourceAsStream("environment.properties"));
        String url = properties.getProperty("io.avalia.fruits.server.url");
        api.getApiClient().setBasePath(url);

    }

    public void setToken(Token token) {
        this.token = token;
    }

    public Token getToken() {
        return token;
    }

    public DefaultApi getApi() {
        return api;
    }


}