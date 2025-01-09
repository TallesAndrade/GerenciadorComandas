package com.talles.GerenciadorComandas.entity;

import com.talles.GerenciadorComandas.model.Status;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "db_comandas")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comanda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nomeCliente;


    private List<ProdutoComanda> produtosComanda = new ArrayList<>();

    private BigDecimal valorTotal;
    private LocalDateTime dataAbertura;
    private Status statusComanda;

}
