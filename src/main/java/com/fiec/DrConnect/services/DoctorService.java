package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.dto.CreateDoctorRequestDto;
import com.fiec.DrConnect.models.entities.Doctor;
import com.fiec.DrConnect.models.entities.User;

import java.util.List;

public interface DoctorService {

    Doctor getProfile(Integer id);

    Doctor findByUser(User user);

    List<Doctor> getAllDoctors();

    Doctor updateUser(
            Doctor doctor,
            CreateDoctorRequestDto createDoctorRequestDto
    );

    void deleteUser(Integer id);

}
