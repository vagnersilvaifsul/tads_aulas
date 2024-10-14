package br.edu.ifsul.cstsi.tads_aulas.produto;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

/*
    O Validation vai depender dos requisitos do sistema. A implementação realizada aqui é uma demonstração
    de sua aplicação.
 */
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorDeCompra;
    private BigDecimal valorDeVenda;
    private String descricao;
    private Boolean situacao;
    private Integer estoque;

}
