package com.talles.GerenciadorComandas.entity;

import com.talles.GerenciadorComandas.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "db_usuarios")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String email;
    private String usuarioLogin;
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    private Role role;



}
