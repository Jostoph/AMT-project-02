package io.avalia.users.api.endpoints;

import io.avalia.users.api.ConnectionApi;
import io.avalia.users.api.model.Credentials;
import io.avalia.users.api.model.Token;
import io.avalia.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ConnectionApiController implements ConnectionApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Token> login(@Valid Credentials credentials) {
        return null;
    }
}
