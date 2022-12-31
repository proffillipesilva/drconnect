package com.fiec.DrConnect.models.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consulta")
public class Appointment implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")

    private Integer id;
    /*Adicionar chaves estrangeiras (id paciente e medico)*/
    private Date startTime;
    private Date endTime;
    private Double price;
    private String title;
    private String description;

    @ManyToOne
    private Doctor doctor;

    @ManyToOne
    private Patient patient;
}
