package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Convenio;
import com.fiec.DrConnect.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConvenioRepository extends JpaRepository<Convenio, Integer> {
}
