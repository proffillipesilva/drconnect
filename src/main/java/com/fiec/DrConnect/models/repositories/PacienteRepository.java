package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Integer> {

    Optional<Paciente> findByEmailAndPassword(String email, String password);
    Optional<Paciente> findByEmail(String email);
}
