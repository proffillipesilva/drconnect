package com.fiec.DrConnect.models.dto;


import com.fiec.DrConnect.models.entities.Patient;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PatientDto {
    Integer id;
    String name;
    String phoneNumber;
    String cpf;
    String address;
    String city;
    String state;
    String zipCode;
    String birthDate;

    public static PatientDto convertToPatientDto(Patient patient){
        return PatientDto.builder()
                .id(patient.getId())

                .name(patient.getName())
                .phoneNumber(patient.getPhoneNumber())
                .cpf(patient.getCpf())
                .address(patient.getAddress())
                .city(patient.getCity())
                .state(patient.getState())

                .zipCode(patient.getZipCode())
                .birthDate(patient.getBirthDate().toString())
                .build();
    }
}
