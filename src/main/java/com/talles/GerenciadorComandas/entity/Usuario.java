package com.talles.GerenciadorComandas.entity;

import com.talles.GerenciadorComandas.model.Role;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
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
    private Long id;
    private String nome;
    private String email;
    private String usuarioLogin;
    private String senha;
    private Role role;



}
