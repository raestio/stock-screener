package com.rasto.stockscreener.service;

import com.rasto.stockscreener.data.entity.User;

public interface UserService {
    User findById(Integer id);
    User findByUsername(String username);
    User save(User user);
}
