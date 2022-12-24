package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Medico;
import com.fiec.DrConnect.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Integer> {

    Optional<Medico> findByEmailAndPassword(String email, String password);
    Optional<Medico> findByEmail(String email);
}
