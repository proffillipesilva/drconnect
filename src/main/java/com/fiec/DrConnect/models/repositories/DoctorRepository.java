package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Doctor;
import com.fiec.DrConnect.models.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DoctorRepository extends JpaRepository<Doctor, Integer> {

    Optional<Doctor> findDoctorByUser(User user);
}
