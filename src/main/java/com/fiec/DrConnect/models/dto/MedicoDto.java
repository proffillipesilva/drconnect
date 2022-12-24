package com.fiec.DrConnect.models.dto;

import com.fiec.DrConnect.models.entities.Medico;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class MedicoDto {
    Integer id;
    String email;
    String password;
    String name;
    String phoneNumber;
    String especialidade;
    String crm;
    String rua;
    String bairro;
    String cidade;
    String numero;
    String estado;
    String cep;
    String dataNasc;

    public static MedicoDto convertToMedicoDto(Medico medico) {
        return MedicoDto.builder()
                .id(medico.getId())
                .email(medico.getEmail())
                .password(medico.getPassword())
                .name(medico.getName())
                .phoneNumber(medico.getPhoneNumber())
                .especialidade(medico.getEspecialidade())
                .crm(medico.getCrm())
                .rua(medico.getRua())
                .bairro(medico.getBairro())
                .cidade(medico.getCidade())
                .numero(medico.getNumero())
                .estado(medico.getEstado())
                .cep(medico.getCep())
                .dataNasc(medico.getDataNasc())
                .build();
    }
}
