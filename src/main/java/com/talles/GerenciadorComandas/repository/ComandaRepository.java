package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Comanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComandaRepository extends JpaRepository<Comanda,Long> {
}
