package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class AuthServiceImpl implements AuthService {

    @Autowired
    private DaoAuthenticationProvider daoAuthenticationProvider;

    @Override
    public boolean login(String username, String password) {
        try {
            Authentication auth = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authenticated = daoAuthenticationProvider.authenticate(auth);
            return authenticated.isAuthenticated();
        } catch (AuthenticationException ex) {
            return false;
        }
    }
}
