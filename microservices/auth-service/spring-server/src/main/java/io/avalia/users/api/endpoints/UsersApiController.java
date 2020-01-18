package io.avalia.users.api.endpoints;

import io.avalia.users.api.UsersApi;
import io.avalia.users.api.model.UserDTO;
import io.avalia.users.business.AuthenticationService;
import io.avalia.users.business.models.AuthInfo;
import io.avalia.users.entities.UserEntity;
import io.avalia.users.api.model.User;
import io.avalia.users.repositories.UserRepository;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@javax.annotation.Generated(value = "io.swagger.codegen.languages.SpringCodegen", date = "2017-07-26T19:36:34.802Z")

@Controller
public class UsersApiController implements UsersApi {

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authentication;

    @Autowired
    HttpServletRequest request;

    @Override
    public ResponseEntity<Void> createUser(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                           @ApiParam(value = "" ,required=true )  @Valid @RequestBody User user) {

        AuthInfo authInfo = (AuthInfo) request.getAttribute("auth-info");

        // only Admins can create users
        if(authInfo != null && authInfo.isAdmin()) {
            UserEntity newUserEntity = toUserEntity(user);

            // user already in the database
            if(userRepository.existsById(newUserEntity.getEmail())) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build();
            }

            // hash password
            newUserEntity.setPassword(authentication.hashPassword(newUserEntity.getPassword()));

            userRepository.save(newUserEntity);

            return ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Void> changePassword(@ApiParam(value = "",required=true) @PathVariable("userId") String userId,
                                               @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization,
                                               @ApiParam(value = "" ,required=true )  @Valid @RequestBody String password) {

        AuthInfo authInfo = (AuthInfo) request.getAttribute("auth-info");

        // only Admins and owner of account can modify the password
        if(authInfo != null && (authInfo.isAdmin() || authInfo.getEmail().equals(userId))) {
            if(userRepository.existsById(userId)) {
                UserEntity entity = userRepository.findById(userId).get();
                entity.setPassword(authentication.hashPassword(password));
                userRepository.save(entity);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<Void> deleteUser(@ApiParam(value = "",required=true) @PathVariable("userId") String userId,
                                           @ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {

        AuthInfo authInfo = (AuthInfo) request.getAttribute("auth-info");

        // only Admins can delete and account
        if(authInfo != null && authInfo.isAdmin()) {
            if(userRepository.existsById(userId)) {
                userRepository.deleteById(userId);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.notFound().build();
            }
        } else {
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @Override
    public ResponseEntity<List<UserDTO>> getUsers(@ApiParam(value = "" ,required=true) @RequestHeader(value="Authorization", required=true) String authorization) {

        AuthInfo authInfo = (AuthInfo) request.getAttribute("auth-info");

        // only connected users can get the list of users
        if(authInfo != null) {
            List<UserDTO> usersDTO = new ArrayList<>();
            for (UserEntity userEntity : userRepository.findAll()) {
                usersDTO.add(toUserDTO(userEntity));
            }
            return ResponseEntity.ok(usersDTO);
        } else {
            System.out.println("auth is null");
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
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
