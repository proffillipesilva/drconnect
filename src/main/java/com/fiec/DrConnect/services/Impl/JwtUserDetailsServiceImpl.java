package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.entities.Medico;
import com.fiec.DrConnect.models.entities.Paciente;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.models.repositories.MedicoRepository;
import com.fiec.DrConnect.models.repositories.PacienteRepository;
import com.fiec.DrConnect.services.JwtUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsServiceImpl implements JwtUserDetailsService {

    @Autowired
    PacienteRepository pacienteRepository;

    @Autowired
    MedicoRepository medicoRepository;

    @Override
    public Paciente loadByEmail(String email) {
        return pacienteRepository.findByEmail(email).orElseThrow();
    }

}
