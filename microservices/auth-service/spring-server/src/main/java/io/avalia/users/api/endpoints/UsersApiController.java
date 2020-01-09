package io.avalia.users.api.endpoints;

import io.avalia.users.api.UsersApi;
import io.avalia.users.api.model.UserDTO;
import io.avalia.users.entities.UserEntity;
import io.avalia.users.api.model.User;
import io.avalia.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Override
    public ResponseEntity<Void> createUser(@ApiParam(value = "", required = true) @Valid @RequestBody User user) {
        // TODO : add errors and unauthorized
        UserEntity newUserEntity = toUserEntity(user);

        // user already in the database
        if(userRepository.existsById(newUserEntity.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).build();
        }

        userRepository.save(newUserEntity);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Override
    public ResponseEntity<Void> changePassword(String userId, @Valid String password) {
        // TODO : add unauthorized
        // TODO : check password length (validity)
        if(userRepository.existsById(userId)) {
            userRepository.findById(userId).get().setPassword(password);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(String userId) {
        // TODO : add unauthorized
        if(userRepository.existsById(userId)) {
            userRepository.deleteById(userId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers() {
        List<UserDTO> usersDTO = new ArrayList<>();
        for (UserEntity userEntity : userRepository.findAll()) {
            usersDTO.add(toUserDTO(userEntity));
        }
        return ResponseEntity.ok(usersDTO);
    }


    private UserEntity toUserEntity(User user) {
        UserEntity entity = new UserEntity();
        entity.setEmail(user.getEmail());
        entity.setFirstName(user.getFirstName());
        entity.setLastName(user.getLastName());
        entity.setPassword(user.getPassword());
        entity.setAdmin(user.getIsAdmin());
        return entity;
    }

    private UserDTO toUserDTO(UserEntity entity) {
        UserDTO userDTO = new UserDTO();
        userDTO.setEmail(entity.getEmail());
        userDTO.setFirstName(entity.getFirstName());
        userDTO.setLastName(entity.getLastName());
        return userDTO;
    }

}
