package com.fiec.DrConnect.services.Impl;

import com.fiec.DrConnect.models.dto.AgendaResponse;
import com.fiec.DrConnect.models.entities.Appointment;
import com.fiec.DrConnect.models.entities.Doctor;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.enums.AgendaStatus;
import com.fiec.DrConnect.models.repositories.AppointmentRepository;
import com.fiec.DrConnect.models.repositories.DoctorRepository;
import com.fiec.DrConnect.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AppointmentServiceImpl implements AppointmentService {


    @Autowired
    DoctorRepository doctorRepository;

    @Autowired
    AppointmentRepository appointmentRepository;

    @Override
    public List<AgendaResponse> getDoctorAgenda(Patient patient, Integer doctorId) {
        Doctor currentDoctor = doctorRepository.findById(doctorId).orElseThrow();
        List<Appointment> consultas = appointmentRepository.findAppointmentByDoctor(currentDoctor);
        if(consultas == null) return Collections.emptyList();
        return consultas.stream().map(c -> AgendaResponse.builder()
                .startTime(c.getStartTime().toString())
                .endTime(c.getEndTime().toString())
                .agendaStatus( patient.getId().equals(c.getPatient().getId()) ? AgendaStatus.FREE : AgendaStatus.OCCUPIED)
                .title(c.getTitle())
                .build()).collect(Collectors.toList());


    }
}
