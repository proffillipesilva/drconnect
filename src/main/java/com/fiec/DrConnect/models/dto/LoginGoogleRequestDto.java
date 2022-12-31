package com.fiec.DrConnect.models.dto;

import com.fiec.DrConnect.models.enums.UserRole;
import lombok.Data;

@Data
public class LoginGoogleRequestDto {
    private String tokenId;
    private UserRole userRole;
}
