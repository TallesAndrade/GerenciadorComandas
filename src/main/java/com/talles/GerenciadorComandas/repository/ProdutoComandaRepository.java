package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.ProdutoComanda;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoComandaRepository extends JpaRepository<ProdutoComanda,Long> {
}
