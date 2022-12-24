package com.fiec.DrConnect.models.entities;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="convenio")
public class Convenio {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name="id_convenio", nullable=false)
    private Integer id;

    private String nome_convenio;
}
