package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.services.GoogleService;
import com.fiec.DrConnect.models.dto.LoginGoogleRequestDto;
import com.fiec.DrConnect.models.dto.LoginResponseDto;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    GoogleService googleService;


    @PostMapping("/signIn")
    public LoginResponseDto signIn(@RequestBody LoginGoogleRequestDto loginGoogleRequestDto) throws GeneralSecurityException, IOException, HttpException {
        return googleService.signIn(loginGoogleRequestDto);
    }
}
