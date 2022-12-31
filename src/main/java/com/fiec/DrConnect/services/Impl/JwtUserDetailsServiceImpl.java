package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.models.repositories.UserRepository;
import com.fiec.DrConnect.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public User loadByEmail(String email) {
        return userRepository.findUserByEmail(email).orElseThrow();
    }

    public User createTempUser(String email) {
        return userRepository.save(User.builder().email(email).build());
    }

}
