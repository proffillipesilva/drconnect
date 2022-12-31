package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.dto.CreatePatientRequestDto;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.entities.User;

import java.util.List;

public interface PatientService {

    Patient findByUser(User user);

    Patient getProfile(Integer id);

    List<Patient> getAllPatients();

    Patient updateUser(
            Patient patient,
            CreatePatientRequestDto createPatientRequestDto
    );

    void deleteUser(Integer id);
}
