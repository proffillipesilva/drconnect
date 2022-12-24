package com.fiec.DrConnect.models.dto;

import com.fiec.DrConnect.models.entities.Paciente;
import com.fiec.DrConnect.models.entities.User;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PacienteDto {
    Integer id;
    String email;
    String password;
    String name;
    String phoneNumber;
    String cpf;
    String rua;
    String numero;
    String bairro;
    String cidade;
    String estado;
    String cep;
    String dataNasc;

    public static PacienteDto convertToPacienteDto(Paciente paciente){
        return PacienteDto.builder()
                .id(paciente.getId())
                .email(paciente.getEmail())
                .password(paciente.getPassword())
                .name(paciente.getName())
                .phoneNumber(paciente.getPhoneNumber())
                .cpf(paciente.getCpf())
                .rua(paciente.getRua())
                .numero(paciente.getNumero())
                .bairro(paciente.getBairro())
                .cidade(paciente.getCidade())
                .cep(paciente.getCep())
                .dataNasc(paciente.getDataNasc())
                .build();
    }
}
