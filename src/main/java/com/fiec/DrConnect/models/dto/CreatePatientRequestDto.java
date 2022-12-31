package com.fiec.DrConnect.models.dto;

import lombok.Data;

@Data
public class CreatePatientRequestDto {
    String name;
    String phoneNumber;
    String cpf;
    String address;
    String city;
    String state;
    String zipCode;
    String birthDate;
}
