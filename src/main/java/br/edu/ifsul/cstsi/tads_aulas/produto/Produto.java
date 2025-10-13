package br.edu.ifsul.cstsi.tads_aulas.produto;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity(name = "Produto")
@Table(name = "produtos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Produto {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private BigDecimal valorDeCompra;
    private BigDecimal valorDeVenda;
    private String descricao;
    private Boolean situacao;
    private Integer estoque;

    public Produto(String nome, BigDecimal valorDeCompra, BigDecimal valorDeVenda, String descricao, Integer estoque) {
        this.nome = nome;
        this.valorDeCompra = valorDeCompra;
        this.valorDeVenda = valorDeVenda;
        this.descricao = descricao;
        this.situacao = true;
        this.estoque = estoque;
    }
}
