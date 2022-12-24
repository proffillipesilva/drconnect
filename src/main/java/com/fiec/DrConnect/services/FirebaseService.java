package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.dto.AuthRequestDto;
import org.springframework.stereotype.Service;

@Service
public interface FirebaseService {

    String signUpUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signUp?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";
    String signInUrl = "https://identitytoolkit.googleapis.com/v1/accounts:signInWithPassword?key=AIzaSyA5VlJEwwc-AkyBayEwLlIvFPisdbxik9o";

    void signUp(AuthRequestDto authRequestDto);
    String signIn(AuthRequestDto authRequestDto);


}
