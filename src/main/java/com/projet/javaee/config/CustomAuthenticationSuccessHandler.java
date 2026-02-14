package com.projet.javaee.config;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;

@Component
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();

        for (GrantedAuthority authority : authorities) {
            String roleCode = authority.getAuthority(); // Contiendra "ROLE_ADMINISTRATEUR", etc.

            if (roleCode.equals("ROLE_ADMINISTRATEUR") || roleCode.equals("ROLE_GESTIONNAIRE")) {
                response.sendRedirect("/dashboard");
                return;
            } else if (roleCode.equals("ROLE_CANDIDAT")) {
                response.sendRedirect("/projets");
                return;
            }
        }
        response.sendRedirect("/");
    }
}
