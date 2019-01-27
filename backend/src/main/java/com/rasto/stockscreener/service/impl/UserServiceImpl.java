package com.rasto.stockscreener.service.impl;

import com.rasto.stockscreener.data.entity.User;
import com.rasto.stockscreener.data.repository.UserRepository;
import com.rasto.stockscreener.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User findById(Integer id) {
        return userRepository.findById(id).get();
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }
}
