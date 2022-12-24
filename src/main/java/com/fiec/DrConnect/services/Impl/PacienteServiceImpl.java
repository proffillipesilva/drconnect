package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.services.PacienteService;
import com.fiec.DrConnect.models.entities.Paciente;
import com.fiec.DrConnect.models.repositories.PacienteRepository;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Service
public class PacienteServiceImpl implements PacienteService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Paciente getProfile(String id) {

        return pacienteRepository.findById(Integer.parseInt(id)).orElseThrow();
    }

    @Override
    public Paciente login(String email, String password) {
        return pacienteRepository.findByEmailAndPassword(email,
                new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8)).orElse(null);
    }

    @Override
    public List<Paciente> getAllUsers() { return pacienteRepository.findAll(); }

    @Override
    public Paciente signUpUser(String name, String email, String password, String phoneNumber, String cpf, String rua, String numero, String bairro, String cidade, String estado, String cep, String dataNasc) {
        return pacienteRepository.save(
                Paciente.builder()
                        .name(name)
                        .password(new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8))
                        .email(email)
                        .phoneNumber(phoneNumber)
                        .cpf(cpf)
                        .rua(rua)
                        .numero(numero)
                        .bairro(bairro)
                        .cidade(cidade)
                        .estado(estado)
                        .cep(cep)
                        .dataNasc(dataNasc)
                        .build()
        );
    }

    @Override
    public Paciente updateUser(Integer id, String name, String password, String phoneNumber, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        Paciente currentPaciente = pacienteRepository.findById(id).orElseThrow();
        currentPaciente.setName(name);
        currentPaciente.setPassword(password);
        currentPaciente.setPhoneNumber(phoneNumber);
        currentPaciente.setRua(rua);
        currentPaciente.setNumero(numero);
        currentPaciente.setBairro(bairro);
        currentPaciente.setCidade(cidade);
        currentPaciente.setEstado(estado);
        currentPaciente.setCep(cep);
        return pacienteRepository.save(currentPaciente);
    }

    @Override
    public void deleteUser(Integer id) {
        pacienteRepository.deleteById(id);
    }
}
