package com.interceptor;

import com.account.session.AccountSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Service
public class TokenValidationInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    AccountSession accountSession;

    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        /*String reqStr = IOUtils.toString(request.getInputStream());
        JSONObject json = new JSONObject(reqStr);
        if (json.has("hash") && json.has("email")) {
            String email = (String) json.get("email");
            String token = (String) json.get("hash");
            boolean isValid = accountSession.isTokenValid(email, token);
            return isValid;
        } else {
            return false;
        }*/
        return true;
    }
}
