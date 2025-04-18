package com.talles.GerenciadorComandas.entity;

import com.talles.GerenciadorComandas.enums.Status;
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

    @OneToMany(mappedBy = "comanda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProdutoComanda> produtosComanda = new ArrayList<>();

    @Column(precision = 10, scale = 2)
    private BigDecimal valorTotal;
    private LocalDateTime dataAbertura;

    @Enumerated(EnumType.ORDINAL)
    private Status statusComanda;

}
