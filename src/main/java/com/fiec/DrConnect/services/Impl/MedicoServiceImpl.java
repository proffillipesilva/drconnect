package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.entities.Medico;
import com.fiec.DrConnect.models.repositories.MedicoRepository;
import com.fiec.DrConnect.services.MedicoService;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;
import java.util.List;



@Service
public class MedicoServiceImpl implements MedicoService {

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public Medico getProfile(String id) {

        return medicoRepository.findById(Integer.parseInt(id)).orElseThrow();
    }

    @Override
    public Medico login(String email, String password) {
        return medicoRepository.findByEmailAndPassword(email,
                new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8)).orElse(null);
    }

    @Override
    public List<Medico> getAllUsers() { return medicoRepository.findAll(); }

    @Override
    public Medico signUpUser(String name, String email, String password, String phoneNumber, String especialidade, String crm, String rua, String bairro, String cidade, String numero, String estado, String cep, String dataNasc) {
        return medicoRepository.save(
                Medico.builder()
                        .name(name)
                        .email(email)
                        .password(new String(DigestUtils.sha3_256(password), StandardCharsets.UTF_8))
                        .phoneNumber(phoneNumber)
                        .estado(estado)
                        .crm(crm)
                        .rua(rua)
                        .bairro(bairro)
                        .cidade(cidade)
                        .numero(numero)
                        .estado(estado)
                        .cep(cep)
                        .dataNasc(dataNasc)
                        .build()
        );
    }

    @Override
    public Medico updateUser(Integer id, String name, String password, String phoneNumber, String especialidade, String crm, String rua, String numero, String bairro, String cidade, String estado, String cep) {
        Medico currentMedico = medicoRepository.findById(id).orElseThrow();
        currentMedico.setName(name);
        currentMedico.setPassword(password);
        currentMedico.setPhoneNumber(phoneNumber);
        currentMedico.setEspecialidade(especialidade);
        currentMedico.setCrm(crm);
        currentMedico.setRua(rua);
        currentMedico.setNumero(numero);
        currentMedico.setBairro(bairro);
        currentMedico.setCidade(cidade);
        currentMedico.setCep(cep);
        return medicoRepository.save(currentMedico);
    }

    @Override
    public void deleteUser(Integer id) { medicoRepository.deleteById(id); }
}
