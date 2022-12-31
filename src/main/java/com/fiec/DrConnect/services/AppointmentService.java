package com.fiec.DrConnect.services;

import com.fiec.DrConnect.models.dto.AgendaResponse;
import com.fiec.DrConnect.models.entities.Patient;

import java.util.List;

public interface AppointmentService {
   List<AgendaResponse> getDoctorAgenda(Patient patient, Integer doctorId);
}
