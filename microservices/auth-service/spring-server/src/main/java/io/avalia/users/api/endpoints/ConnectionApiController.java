package io.avalia.users.api.endpoints;

import com.auth0.jwt.exceptions.JWTCreationException;
import io.avalia.users.api.ConnectionApi;
import io.avalia.users.api.model.Credentials;
import io.avalia.users.api.model.Token;
import io.avalia.users.api.model.User;
import io.avalia.users.business.AuthenticationService;
import io.avalia.users.business.AuthorizationService;
import io.avalia.users.entities.UserEntity;
import io.avalia.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class ConnectionApiController implements ConnectionApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authentication;

    @Autowired
    AuthorizationService authorization;

    @Override
    public ResponseEntity<Token> login(@ApiParam(value = "" ,required=true )  @Valid @RequestBody Credentials credentials) {
        if(userRepository.existsById(credentials.getEmail())) {

            User user = toUser(userRepository.findById(credentials.getEmail()).get());
            if(authentication.checkPassword(credentials.getPassword(), userRepository.findById(credentials.getEmail()).get().getPassword())) {
                try {
                    Token token = authorization.generateToken(user);
                    return ResponseEntity.ok(token);
                } catch (JWTCreationException e) {
                    return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
                }
            }
        }

        return ResponseEntity.status(401).build();
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setEmail(entity.getEmail());
        user.setFirstName(entity.getFirstName());
        user.setLastName(entity.getLastName());
        user.setIsAdmin(entity.getAdmin());
        user.setPassword(entity.getPassword());
        return user;
    }
}
