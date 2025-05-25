package com.talles.GerenciadorComandas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "db_estoque")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Estoque {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomeProduto;
    private Long idProduto;

    private int quantidade;

    public Estoque(Produto produto) {
        this.nomeProduto = produto.getNome();
        this.idProduto = produto.getId();
        this.quantidade = 0;
    }
}
