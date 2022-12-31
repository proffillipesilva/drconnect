package com.fiec.DrConnect.models.repositories;


import com.fiec.DrConnect.models.entities.Appointment;
import com.fiec.DrConnect.models.entities.Doctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Integer> {

    List<Appointment> findAppointmentByDoctor(Doctor doctor);
}
