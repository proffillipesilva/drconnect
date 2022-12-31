package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.dto.CreatePatientRequestDto;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.services.PatientService;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.repositories.PatientRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.sql.Date;
import java.util.List;

@Service
public class PatientServiceImpl implements PatientService {

    @Autowired
    PatientRepository patientRepository;


    @Override
    public Patient findByUser(User user) {
        return patientRepository.findPatientByUser(user).orElse(null);
    }

    @Override
    public Patient getProfile(Integer id) {
        return null;
    }

    @Override
    public List<Patient> getAllPatients() {
        return null;
    }

    @Override
    public Patient updateUser(Patient patient, CreatePatientRequestDto createPatientRequestDto) {
        patient.setName(createPatientRequestDto.getName());
        patient.setCity(createPatientRequestDto.getCity());
        patient.setPhoneNumber(createPatientRequestDto.getPhoneNumber());
        patient.setAddress(createPatientRequestDto.getAddress());
        patient.setCpf(createPatientRequestDto.getCpf());
        patient.setState(createPatientRequestDto.getState());
        patient.setZipCode(createPatientRequestDto.getZipCode());
        patient.setBirthDate(Date.valueOf(createPatientRequestDto.getBirthDate()));
        return patientRepository.save(patient);
    }

    @Override
    public void deleteUser(Integer id) {
        patientRepository.deleteById(id);
    }
}
