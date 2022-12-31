package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.dto.CreateDoctorRequestDto;
import com.fiec.DrConnect.models.entities.Doctor;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.models.repositories.DoctorRepository;
import com.fiec.DrConnect.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class DoctorServiceImpl implements DoctorService {

    @Autowired
    DoctorRepository doctorRepository;

    @Override
    public Doctor getProfile(Integer id) {

        return doctorRepository.findById(id).orElse(null);
    }

    @Override
    public Doctor findByUser(User user) {
        return doctorRepository.findDoctorByUser(user).orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctors() { return doctorRepository.findAll(); }

    @Override
    public Doctor updateUser(Doctor currentDoctor, CreateDoctorRequestDto createDoctorRequestDto) {
        currentDoctor.setName(createDoctorRequestDto.getName());
        currentDoctor.setPhoneNumber(createDoctorRequestDto.getPhoneNumber());
        currentDoctor.setSpecialization(createDoctorRequestDto.getSpecialization());
        currentDoctor.setCrm(createDoctorRequestDto.getCrm());
        currentDoctor.setAddress(createDoctorRequestDto.getAddress());
        currentDoctor.setState(createDoctorRequestDto.getState());
        currentDoctor.setWorkEmail(createDoctorRequestDto.getWorkEmail());
        currentDoctor.setCity(createDoctorRequestDto.getCity());
        currentDoctor.setZipCode(createDoctorRequestDto.getZipCode());
        currentDoctor.setBirthDate(Date.valueOf(createDoctorRequestDto.getBirthDate()));
        return doctorRepository.save(currentDoctor);
    }

    @Override
    public void deleteUser(Integer id) { doctorRepository.deleteById(id); }


}
