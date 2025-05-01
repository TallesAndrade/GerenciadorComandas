package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Comanda;
import com.talles.GerenciadorComandas.enums.Status;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ComandaRepository extends JpaRepository<Comanda,Long> {
    List<Comanda> findByStatusComanda(Status statusComanda);
}
