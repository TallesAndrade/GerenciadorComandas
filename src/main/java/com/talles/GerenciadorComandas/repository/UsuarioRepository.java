package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
}
