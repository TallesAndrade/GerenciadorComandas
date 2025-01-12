package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
}
