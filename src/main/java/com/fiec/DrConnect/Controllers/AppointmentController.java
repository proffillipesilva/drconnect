package com.fiec.DrConnect.Controllers;


import com.fiec.DrConnect.models.dto.AgendaResponse;
import com.fiec.DrConnect.models.entities.Appointment;
import com.fiec.DrConnect.models.entities.Patient;
import com.fiec.DrConnect.models.entities.User;
import com.fiec.DrConnect.services.AppointmentService;
import com.fiec.DrConnect.services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.jms.*;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/appointments")
public class AppointmentController {

    private static final String DESTINATION_NAME = "drconnect_queue";

    @Autowired(required = false)
    private JmsTemplate jmsTemplate;

    @Autowired
    private AppointmentService appointmentService;

    @Autowired
    private PatientService patientService;

    @PostMapping
    public void MakeAnAppointment() throws JMSException {

        jmsTemplate.convertAndSend(DESTINATION_NAME, Appointment.builder()
                        .startTime(new Date())
                        .endTime(new Date())
                        .id(5)
                        .price(55.0)
                .build());

    }

    @GetMapping("/doctor/{doctorId}/agenda")
    public List<AgendaResponse> getDoctorAgenda(@PathVariable("doctorId") Integer doctorId, Authentication authentication){
        User user = (User) authentication.getPrincipal();
        Patient patient = patientService.findByUser(user);
        return appointmentService.getDoctorAgenda(patient, doctorId);
    }



}
