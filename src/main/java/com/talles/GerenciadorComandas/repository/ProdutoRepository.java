package com.talles.GerenciadorComandas.repository;

import com.talles.GerenciadorComandas.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto,Long> {
    Optional<Produto> findByIdAndAtivoTrue(Long id);

    List<Produto> findByAtivoTrue();
}
