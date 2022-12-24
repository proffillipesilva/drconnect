package com.fiec.DrConnect.models.dto;

import lombok.Data;

@Data
public class CreateMedicoRequestDto {
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
}
