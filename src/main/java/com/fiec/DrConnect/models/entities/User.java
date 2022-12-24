package com.fiec.DrConnect.models.entities;

import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;

@Getter
@Setter
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@MappedSuperclass
public abstract class User implements UserDetails {

    @Column(nullable = false)
    protected String email;

    @Column(nullable = false)
    protected String password;

    @Column(name="nome_completo", nullable = false)
    protected String name;

    @Column(name="telefone", nullable = false)
    protected String phoneNumber;

    /* Adicionar chaves estrangeiras
    * VER SOBRE A SENHA*/


}
