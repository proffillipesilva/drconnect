package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.Utils.CustomException;
import com.fiec.DrConnect.Utils.ResultCodesException;
import com.fiec.DrConnect.models.dto.CreatePacienteRequestDto;
import com.fiec.DrConnect.models.dto.LoginRequestDto;
import com.fiec.DrConnect.models.dto.PacienteDto;
import com.fiec.DrConnect.models.entities.Paciente;
import com.fiec.DrConnect.services.PacienteService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/paciente")
public class PacienteController {

    @Autowired
    PacienteService pacienteService;

    @GetMapping
    public List<PacienteDto> getAllUsers() {
        return pacienteService.getAllUsers().stream().map(PacienteDto::convertToPacienteDto).collect(Collectors.toList());
    }

    @PostMapping
    public PacienteDto signUpUser(@RequestBody CreatePacienteRequestDto createPacienteRequestDto){

        try {
            return PacienteDto.convertToPacienteDto(pacienteService.signUpUser(
                    createPacienteRequestDto.getEmail(),
                    createPacienteRequestDto.getPassword(),
                    createPacienteRequestDto.getName(),
                    createPacienteRequestDto.getPhoneNumber(),
                    createPacienteRequestDto.getCpf(),
                    createPacienteRequestDto.getRua(),
                    createPacienteRequestDto.getNumero(),
                    createPacienteRequestDto.getBairro(),
                    createPacienteRequestDto.getCidade(),
                    createPacienteRequestDto.getEstado(),
                    createPacienteRequestDto.getCep(),
                    createPacienteRequestDto.getDataNasc()
            ));
        } catch(Exception ex){
            throw new CustomException(ResultCodesException.USER_ALREADY_EXISTS);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        Paciente paciente = pacienteService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );

        if(paciente == null){
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(Response.SC_OK).build();
        }
    }

    @GetMapping("/{id}")
    public PacienteDto getProfile(@PathVariable("id") String id){
        return PacienteDto.convertToPacienteDto(pacienteService.getProfile(id));
    }

    @PutMapping("/{id}")
    public PacienteDto editUser(@RequestBody CreatePacienteRequestDto createPacienteRequestDto, @PathVariable("id") Integer id){
        return PacienteDto.convertToPacienteDto(pacienteService.updateUser(id,
                createPacienteRequestDto.getName(),
                createPacienteRequestDto.getPassword(),
                createPacienteRequestDto.getPhoneNumber(),
                createPacienteRequestDto.getRua(),
                createPacienteRequestDto.getNumero(),
                createPacienteRequestDto.getBairro(),
                createPacienteRequestDto.getCidade(),
                createPacienteRequestDto.getEstado(),
                createPacienteRequestDto.getCep()
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){
        pacienteService.deleteUser(id);
    }
}
