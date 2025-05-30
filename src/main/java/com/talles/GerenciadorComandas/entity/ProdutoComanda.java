package com.talles.GerenciadorComandas.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

    @ManyToOne
    @JoinColumn(name = "comanda_id")
    @JsonIgnore
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;



    public ProdutoComanda(Comanda comanda,Produto produto, int quantidade) {
        this.comanda = comanda;
        this.produto = produto;
        this.quantidade = quantidade;
        this.valorUnitario = produto.getPreco();
        this.valorTotal = produto.getPreco().multiply(new BigDecimal(quantidade));
    }
}

