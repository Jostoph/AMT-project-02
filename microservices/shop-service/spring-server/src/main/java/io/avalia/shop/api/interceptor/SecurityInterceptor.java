package io.avalia.shop.api.interceptor;

import io.avalia.shop.business.AuthorizationService;
import io.avalia.shop.business.models.AuthInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class SecurityInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AuthorizationService authorization;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        AuthInfo info = authorization.decodeToken(request.getHeader("Authorization"));

        if(info == null) {
            response.setStatus(HttpServletResponse.SC_FORBIDDEN);
            return false;
        } else {
            request.setAttribute("auth-info", info);
            return true;
        }
    }
}
