package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.services.FirebaseService;
import com.fiec.DrConnect.services.JwtUserDetailsService;
import com.fiec.DrConnect.Utils.JwtTokenUtil;
import com.fiec.DrConnect.models.dto.AuthRequestDto;
import com.fiec.DrConnect.models.dto.LoginGoogleRequestDto;
import com.fiec.DrConnect.models.dto.LoginResponseDto;
import com.fiec.DrConnect.models.entities.User;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import org.apache.http.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.Collections;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    FirebaseService firebaseService;

    @Autowired
    JwtUserDetailsService jwtUserDetailsService;

    @Value("${spring.security.oauth2.client.registration.google.client-id}")
    private String clientId;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    @PostMapping("/signUp")
    public void signUp(@RequestBody AuthRequestDto authRequestDto){
        firebaseService.signUp(authRequestDto);
    }

    @PostMapping("/signIn")
    public LoginResponseDto signIn(@RequestBody AuthRequestDto authRequestDto){
        String token = firebaseService.signIn(authRequestDto);
        return LoginResponseDto.builder()
                .token(token)
                .build();
    }

    @PostMapping("/loginWithGoogle")
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

            User user = jwtUserDetailsService.loadByEmail(email);


            String token = jwtTokenUtil.generateToken(user);
            return LoginResponseDto.builder()
                    .token(token)
                    .build();
        }
        throw new HttpException();
    }
}
