package io.avalia.users.api.endpoints;

import io.avalia.users.api.UsersApi;
import io.avalia.users.entities.UserEntity;
import io.avalia.users.api.model.User;
import io.avalia.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    public ResponseEntity<Object> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        UserEntity newUserEntity = toUserEntity(user);
        userRepository.save(newUserEntity);
        Long id = newUserEntity.getId();

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}")
                .buildAndExpand(newUserEntity.getId()).toUri();

        return ResponseEntity.created(location).build();
    }

    @Override
    public ResponseEntity<Object> deleteUser(Integer userId) {
        return null;
    }


    public ResponseEntity<List<User>> getUsers() {
        List<User> users = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            users.add(toUser(userEntity));
        }
        /*
        User staticUser = new User();
        staticUser.setColour("red");
        staticUser.setKind("banana");
        staticUser.setSize("medium");
        List<User> users = new ArrayList<>();
        users.add(staticUser);
        */
        return ResponseEntity.ok(users);
    }


    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setColour(user.getColour());
        entity.setKind(user.getKind());
        entity.setSize(user.getSize());
        return entity;
    }

    private User toUser(UserEntity entity) {
        User user = new User();
        user.setColour(entity.getColour());
        user.setKind(entity.getKind());
        user.setSize(entity.getSize());
        return user;
    }

}
