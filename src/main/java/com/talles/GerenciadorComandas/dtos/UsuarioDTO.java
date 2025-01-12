package com.talles.GerenciadorComandas.dtos;

import com.talles.GerenciadorComandas.enums.Role;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class UsuarioDTO {
    private Long id;
    private String nome;
    private String email;
    private String usuarioLogin;
    private String senha;

    @Enumerated(EnumType.ORDINAL)
    private Role role;
}
