package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.entities.Medico;

import java.util.List;

public interface MedicoService {

    Medico getProfile(String id);

    Medico login(String email, String password);

    List<Medico> getAllUsers();

    Medico signUpUser(
            String name,
            String email,
            String password,
            String phoneNumber,
            String especialidade,
            String crm,
            String rua,
            String bairro,
            String cidade,
            String numero,
            String estado,
            String cep,
            String dataNasc
    );

    Medico updateUser(
            Integer id,
            String name,
            String password,
            String phoneNumber,
            String especialidade,
            String crm,
            String rua,
            String numero,
            String bairro,
            String cidade,
            String estado,
            String cep
    );

    void deleteUser(Integer id);
}
