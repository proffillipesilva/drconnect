package com.fiec.DrConnect.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;
import java.util.Date;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Doctor  {
    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "doctor_id", unique = true, nullable = false)
    private Integer id;
    @Column(nullable = false)
    private String crm;
    private String specialization;
    private String address;
    private String city;
    private String state;
    private String zipCode;
    private Date birthDate;
    private String name;
    private String phoneNumber;
    private String workEmail;

    private User user;

}
