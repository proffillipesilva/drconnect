package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.services.FirebaseService;
import com.fiec.DrConnect.models.dto.AuthRequestDto;
import com.fiec.DrConnect.models.dto.FirebaseRequestDto;
import com.fiec.DrConnect.models.dto.FirebaseResponseDto;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FirebaseServiceImpl implements FirebaseService {

    @Override
    public void signUp(AuthRequestDto authRequestDto) {
        RestTemplate rt = new RestTemplate();
        FirebaseRequestDto firebaseRequestDto = FirebaseRequestDto.builder()
                .email(authRequestDto.getEmail())
                .password(authRequestDto.getPassword())
                .returnSecureToken(true)
                .build();
        HttpEntity<FirebaseRequestDto> entity = new HttpEntity<>(firebaseRequestDto);
        ResponseEntity<FirebaseResponseDto> res = rt.exchange(signUpUrl, HttpMethod.POST, entity, FirebaseResponseDto.class);
        FirebaseResponseDto firebaseResponseDto = res.getBody();

    }

    @Override
    public String signIn(AuthRequestDto authRequestDto) {
        RestTemplate rt = new RestTemplate();
        FirebaseRequestDto firebaseRequestDto = FirebaseRequestDto.builder()
                .email(authRequestDto.getEmail())
                .password(authRequestDto.getPassword())
                .returnSecureToken(true)
                .build();
        HttpEntity<FirebaseRequestDto> entity = new HttpEntity<>(firebaseRequestDto);
        ResponseEntity<FirebaseResponseDto> res = rt.exchange(signInUrl, HttpMethod.POST, entity, FirebaseResponseDto.class);
        FirebaseResponseDto firebaseResponseDto = res.getBody();
        return firebaseResponseDto.getIdToken();
    }
}
