package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.Utils.JwtTokenUtil;
import com.fiec.DrConnect.models.dto.LoginGoogleRequestDto;
import com.fiec.DrConnect.models.dto.LoginResponseDto;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.models.enums.UserRole;
import com.fiec.DrConnect.services.GoogleService;
import com.fiec.DrConnect.services.JwtUserDetailsService;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@Service
public class GoogleServiceImpl implements GoogleService {

    @Autowired
    private JwtUserDetailsService jwtUserDetailsService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public LoginResponseDto signIn(@RequestBody LoginGoogleRequestDto loginGoogleRequestDto) throws GeneralSecurityException, IOException, HttpException {
        HttpTransport httpTransport = new NetHttpTransport();
        GsonFactory jsonFactory = new GsonFactory();


        GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(httpTransport, jsonFactory)
                .setAudience(Collections.singletonList(clientId))
                .build();


        GoogleIdToken googleTokenResponse = verifier.verify(loginGoogleRequestDto.getTokenId());

        if(googleTokenResponse != null){
            GoogleIdToken.Payload payload = googleTokenResponse.getPayload();

            String userId = payload.getSubject();
            System.out.println("User ID: " + userId);

            String email = payload.getEmail();
            String name = (String) payload.get("name");
            String pictureUrl = (String) payload.get("picture");
            String token;
            User user = jwtUserDetailsService.loadByEmail(email);
            // Cria usuario quando nao tem na base ainda
            if(user == null){
                user = jwtUserDetailsService.createTempUser(email);
            }
            if(UserRole.ROLE_PATIENT.equals(loginGoogleRequestDto.getUserRole())) {
                token = jwtTokenUtil.generateToken(user, UserRole.ROLE_PATIENT);
            }
            else {
                token = jwtTokenUtil.generateToken(user, UserRole.ROLE_DOCTOR);
            }
            return LoginResponseDto.builder()
                    .token(token)
                    .build();
        }
        throw new HttpException();
    }
}
