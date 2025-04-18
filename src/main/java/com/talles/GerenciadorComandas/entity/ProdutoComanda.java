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

    @ManyToOne
    @JoinColumn(name = "comanda_id")
    private Comanda comanda;

    @ManyToOne
    @JoinColumn(name = "produto_id")
    private Produto produto;

    private int quantidade;

    private BigDecimal valorUnitario;

    private BigDecimal valorTotal;

    @PostLoad
    public void postLoad(){
        if(produto != null){
            this.valorUnitario = produto.getPreco();
            this.valorTotal = valorUnitario.multiply(new BigDecimal(quantidade));
        }
    }   }
