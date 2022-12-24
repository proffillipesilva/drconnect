package com.fiec.DrConnect.Controllers;


import com.fiec.DrConnect.models.entities.Consulta;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.jms.*;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/consultas")
public class ConsultaController {

    private static final String DESTINATION_NAME = "drconnect_queue";

    @Autowired(required = false)
    private JmsTemplate jmsTemplate;

    @PostMapping
    public void MarcaConsulta() throws JMSException {

        jmsTemplate.convertAndSend(DESTINATION_NAME, Consulta.builder()
                        .data_consulta("")
                        .hora_consulta("")
                        .id(5)
                        .valor_consulta("")
                .build());

    }



}
