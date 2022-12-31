package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.entities.User;

public interface JwtUserDetailsService {
    User loadByEmail(String email);
    User createTempUser(String email);
}
