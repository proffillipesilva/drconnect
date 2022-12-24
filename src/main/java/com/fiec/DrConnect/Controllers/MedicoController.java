package com.fiec.DrConnect.Controllers;

import com.fiec.DrConnect.Utils.CustomException;
import com.fiec.DrConnect.Utils.ResultCodesException;
import com.fiec.DrConnect.models.dto.CreateMedicoRequestDto;
import com.fiec.DrConnect.models.dto.LoginRequestDto;
import com.fiec.DrConnect.models.dto.MedicoDto;
import com.fiec.DrConnect.models.entities.Medico;
import com.fiec.DrConnect.services.MedicoService;
import org.apache.catalina.connector.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/medico")
public class MedicoController {

    @Autowired
    MedicoService medicoService;

    @GetMapping
    public List<MedicoDto> getAllUsers() {
        return medicoService.getAllUsers().stream().map(MedicoDto::convertToMedicoDto).collect(Collectors.toList());
    }

    @PostMapping
    public MedicoDto signUpUser(@RequestBody CreateMedicoRequestDto createMedicoRequestDto){

        try {
            return MedicoDto.convertToMedicoDto(medicoService.signUpUser(
                    createMedicoRequestDto.getEmail(),
                    createMedicoRequestDto.getPassword(),
                    createMedicoRequestDto.getName(),
                    createMedicoRequestDto.getPhoneNumber(),
                    createMedicoRequestDto.getEspecialidade(),
                    createMedicoRequestDto.getCrm(),
                    createMedicoRequestDto.getRua(),
                    createMedicoRequestDto.getBairro(),
                    createMedicoRequestDto.getCidade(),
                    createMedicoRequestDto.getNumero(),
                    createMedicoRequestDto.getEstado(),
                    createMedicoRequestDto.getCep(),
                    createMedicoRequestDto.getDataNasc()
            ));
        } catch(Exception ex){
            throw new CustomException(ResultCodesException.USER_ALREADY_EXISTS);
        }
    }

    @PostMapping("/login")
    public ResponseEntity login(@RequestBody LoginRequestDto loginRequestDto){

        Medico medico = medicoService.login(
                loginRequestDto.getEmail(),
                loginRequestDto.getPassword()
        );
        if(medico == null){
            return ResponseEntity.status(Response.SC_UNAUTHORIZED).build();
        } else {
            return ResponseEntity.status(Response.SC_OK).build();
        }
    }

    @GetMapping("/{id}")
    public MedicoDto getProfile(@PathVariable("id") String id){
        return MedicoDto.convertToMedicoDto(medicoService.getProfile(id));
    }

    @PutMapping("/{id}")
    public MedicoDto editUser(@RequestBody CreateMedicoRequestDto createMedicoRequestDto, @PathVariable("id") Integer id){
        return MedicoDto.convertToMedicoDto(medicoService.updateUser(id,
                createMedicoRequestDto.getName(),
                createMedicoRequestDto.getPassword(),
                createMedicoRequestDto.getPhoneNumber(),
                createMedicoRequestDto.getEspecialidade(),
                createMedicoRequestDto.getRua(),
                createMedicoRequestDto.getBairro(),
                createMedicoRequestDto.getCidade(),
                createMedicoRequestDto.getNumero(),
                createMedicoRequestDto.getEstado(),
                createMedicoRequestDto.getCep(),
                createMedicoRequestDto.getDataNasc()
        ));
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable("id") Integer id){ medicoService.deleteUser(id); }
}
