package io.avalia.users.api.interceptor;

import io.avalia.users.business.AuthenticationService;
import io.avalia.users.business.AuthorizationService;
import io.avalia.users.business.models.AuthInfo;
import io.avalia.users.entities.UserEntity;
import io.avalia.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AuthorizationService auth;

    @Autowired
    UserRepository userRepository;

    @Autowired
    AuthenticationService authentication;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("In interceptor");

        // TODO remove : used to create a root user
//        UserEntity admin = new UserEntity();
//        admin.setEmail("root");
//        admin.setFirstName("root");
//        admin.setLastName("root");
//        admin.setPassword(authentication.hashPassword("root"));
//        admin.setAdmin(true);
//        if(!userRepository.existsById("root")) {
//            userRepository.save(admin);
//
//            System.out.println("Adding admin");
//            System.out.println("password is " + admin.getPassword());
//        }
        // -- remove

        AuthInfo info = auth.decodeToken(request.getHeader("Authorization"));

        if(info == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            request.setAttribute("auth-info", info);
            return true;
        }
    }
}
