package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.Configuration.IsPatient;
import com.fiec.DrConnect.models.dto.CreatePatientRequestDto;
import com.fiec.DrConnect.models.dto.PatientDto;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    PatientService patientService;

    @GetMapping
    public List<PatientDto> getAllDoctors() {
        return patientService.getAllPatients().stream().map(PatientDto::convertToPatientDto).collect(Collectors.toList());
    }


    @GetMapping("/{id}")
    public PatientDto getProfile(@PathVariable("id") Integer id){
        return PatientDto.convertToPatientDto(patientService.getProfile(id));
    }

    @PutMapping("/editProfile")
    @IsPatient
    public PatientDto editUser(@RequestBody CreatePatientRequestDto createPatientRequestDto, Authentication authentication){
        Patient patient = patientService.findByUser((User) authentication.getPrincipal());
        if(patient == null) throw new RuntimeException();
        return PatientDto.convertToPatientDto(patientService.updateUser(patient,
                createPatientRequestDto
        ));
    }

    @PostMapping("/signUp")
    @IsPatient
    public PatientDto signUpPatient(@RequestBody CreatePatientRequestDto createPatientRequestDto, Authentication authentication){

        User user = (User) authentication.getPrincipal();
        Patient patient = patientService.findByUser(user);
        if(patient != null) throw new RuntimeException();
        patient = new Patient();
        patient.setUser(user);
        return PatientDto.convertToPatientDto(patientService.updateUser(patient,
                createPatientRequestDto
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        patientService.deleteUser(id);
    }
}
