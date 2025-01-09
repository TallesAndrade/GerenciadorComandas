package com.talles.GerenciadorComandas.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Entity
@Table(name = "db_produto_comanda")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoComanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Comanda comanda;

    private Produto produto;
    private int quantidade;
    private BigDecimal valorUnitario = produto.getPreco();
    private BigDecimal valorTotal = valorUnitario.multiply(new BigDecimal(quantidade));
}
