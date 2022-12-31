package com.fiec.DrConnect.models.dto;

import lombok.Data;

@Data
public class CreateDoctorRequestDto {
    String workEmail;
    String name;
    String phoneNumber;
    String specialization;
    String crm;
    String address;
    String city;
    String state;
    String zipCode;
    String birthDate;
}
