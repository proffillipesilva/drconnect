package com.fiec.DrConnect.models.dto;

import lombok.Data;

@Data
public class CreatePacienteRequestDto {
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
}
