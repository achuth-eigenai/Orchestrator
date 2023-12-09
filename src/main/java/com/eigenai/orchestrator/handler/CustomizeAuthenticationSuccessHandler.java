package com.eigenai.orchestrator.handler;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.oidc.user.DefaultOidcUser;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Map;

/**
 * The type Customize authentication success handler.
 */
@Slf4j
@Component
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException {
        for (GrantedAuthority auth : authentication.getAuthorities()) {
            DefaultOidcUser defaultOidcUser = (DefaultOidcUser) authentication.getPrincipal();
            Map<String, Object> userAttributes = defaultOidcUser.getAttributes();
            log.info(userAttributes.toString());
            if ("ROLE_ADMIN".equals(auth.getAuthority())) {
                log.info(userAttributes.get("cognito:username") + " Is Admin!");
            } else if ("ROLE_USER".equals(auth.getAuthority())) {
                log.info(userAttributes.get("cognito:username") + " Is User!");
            }
        }
    }
}