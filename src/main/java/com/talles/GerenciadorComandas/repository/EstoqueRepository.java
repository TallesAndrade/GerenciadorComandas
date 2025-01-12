package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Estoque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EstoqueRepository extends JpaRepository<Estoque,Long> {
}
