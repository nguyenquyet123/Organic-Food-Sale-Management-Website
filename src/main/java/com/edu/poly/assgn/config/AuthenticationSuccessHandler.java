package com.edu.poly.assgn.config;

import java.io.IOException;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import com.edu.poly.assgn.service.SessionService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class AuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

    private final SessionService sessionService;

    public AuthenticationSuccessHandler(SessionService sessionService) {
        this.sessionService = sessionService;
    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
            Authentication authentication) throws ServletException, IOException {

        String username = authentication.getName();
        sessionService.set("customerName", username);

        response.sendRedirect("/shop/home");

        // boolean isStaff = authentication.getAuthorities().stream()
        // .anyMatch(grantedAuthority ->
        // grantedAuthority.getAuthority().equals("ROLE_STAFF"));

        // if (isStaff) {
        // response.sendRedirect("/shop/admin");
        // } else {
        // response.sendRedirect("/shop/home");
        // }

        // super.onAuthenticationSuccess(request, response, authentication);
    }
}
