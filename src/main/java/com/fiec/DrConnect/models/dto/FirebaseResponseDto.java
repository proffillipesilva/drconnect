package com.fiec.DrConnect.models.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class FirebaseResponseDto {
    String idToken;
    String email;
    String refreshToken;
    String expiresIn;
    String localId;
    String registered;
}
