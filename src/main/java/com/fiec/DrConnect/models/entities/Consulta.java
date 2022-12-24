package com.fiec.DrConnect.models.entities;


import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "consulta")
public class Consulta implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_consulta")

    private Integer id;
    /*Adicionar chaves estrangeiras (id paciente e medico)*/
    private String hora_consulta;
    private String data_consulta;
    private String valor_consulta;
}
