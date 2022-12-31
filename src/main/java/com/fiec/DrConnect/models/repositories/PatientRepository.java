package com.fiec.DrConnect.models.repositories;

import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

    Optional<Patient> findPatientByUser(User user);
}
