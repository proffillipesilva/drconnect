package com.fiec.DrConnect.models.dto;

import com.fiec.DrConnect.models.entities.Doctor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class DoctorDto {
    Integer id;
    String workEmail;
    String name;
    String phoneNumber;
    String specialization;
    String crm;
    String address;
    String city;
    String number;
    String state;
    String zipCode;
    String birthDate;

    public static DoctorDto convertToDoctorDto(Doctor doctor) {
        return DoctorDto.builder()
                .id(doctor.getId())
                .workEmail(doctor.getWorkEmail())
                .name(doctor.getName())
                .phoneNumber(doctor.getPhoneNumber())
                .specialization(doctor.getSpecialization())
                .crm(doctor.getCrm())
                .address(doctor.getAddress())
                .city(doctor.getCity())
                .state(doctor.getState())

                .state(doctor.getState())
                .zipCode(doctor.getZipCode())
                .birthDate(doctor.getBirthDate().toString())
                .build();
    }
}
