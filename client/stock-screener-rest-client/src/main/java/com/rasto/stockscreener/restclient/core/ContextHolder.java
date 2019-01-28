package com.rasto.stockscreener.restclient.core;

import com.rasto.stockscreener.model.User;

public class ContextHolder {

    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
