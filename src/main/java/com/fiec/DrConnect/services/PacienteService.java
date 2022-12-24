package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.entities.Paciente;

import java.util.List;

public interface PacienteService {

    Paciente getProfile(String id);

    Paciente login(String email, String password);

    List<Paciente> getAllUsers();

    Paciente signUpUser(
            String name,
            String email,
            String password,
            String phoneNumber,
            String cpf,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String estado,
            String cep,
            String dataNasc
    );

    Paciente updateUser(
            Integer id,
            String name,
            String password,
            String phoneNumber,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String estado,
            String cep
    );

    void deleteUser(Integer id);
}
