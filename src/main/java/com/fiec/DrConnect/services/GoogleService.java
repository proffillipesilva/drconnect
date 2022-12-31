package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.dto.LoginGoogleRequestDto;
import com.fiec.DrConnect.models.dto.LoginResponseDto;
import org.apache.http.HttpException;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.GeneralSecurityException;

public interface GoogleService {

    LoginResponseDto signIn(@RequestBody LoginGoogleRequestDto loginGoogleRequestDto) throws GeneralSecurityException, IOException, HttpException;
}
