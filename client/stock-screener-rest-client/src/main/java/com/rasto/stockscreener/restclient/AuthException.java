package com.rasto.stockscreener.restclient;

public class AuthException extends Exception {

    public AuthException(String message) {
        super(message);
    }

    public AuthException() {
    }
}
