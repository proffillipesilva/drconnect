package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Consulta;
import com.fiec.DrConnect.models.entities.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Integer> {
}
