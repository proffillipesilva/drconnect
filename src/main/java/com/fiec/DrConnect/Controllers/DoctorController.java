package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.Configuration.IsDoctor;
import com.fiec.DrConnect.models.dto.CreateDoctorRequestDto;
import com.fiec.DrConnect.models.dto.DoctorDto;
import com.fiec.DrConnect.models.entities.Doctor;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.services.DoctorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.print.Doc;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/doctors")
public class DoctorController {

    @Autowired
    DoctorService doctorService;

    @GetMapping
    public List<DoctorDto> getAllDoctors() {
        return doctorService.getAllDoctors().stream().map(DoctorDto::convertToDoctorDto).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public DoctorDto getProfile(@PathVariable("id") Integer id){
        return DoctorDto.convertToDoctorDto(doctorService.getProfile(id));
    }

    @PutMapping("/editProfile")
    @IsDoctor
    public DoctorDto editUser(@RequestBody CreateDoctorRequestDto createMedicoRequestDto, Authentication authentication){
        Doctor doctor = (Doctor) authentication.getPrincipal();
        return DoctorDto.convertToDoctorDto(doctorService.updateUser(doctor,
                createMedicoRequestDto
        ));
    }

    @PutMapping("/signUp")
    @IsDoctor
    public DoctorDto signUpDoctor(@RequestBody CreateDoctorRequestDto createMedicoRequestDto, Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Doctor doctor = doctorService.findByUser(user);
        if(doctor != null) throw new RuntimeException();
        doctor = new Doctor();
        doctor.setUser(user);
        return DoctorDto.convertToDoctorDto(doctorService.updateUser(doctor,
                createMedicoRequestDto
        ));
    }


    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){ doctorService.deleteUser(id); }


}
