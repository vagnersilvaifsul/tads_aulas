package br.edu.ifsul.cstsi.tads_aulas.model;

import java.math.BigDecimal;
import java.util.Collection;


public class Produto {
    private Long id;
    private String nome;
    private BigDecimal valorDeCompra;
    private BigDecimal valorDeVenda;
    private String descricao;
    private Boolean situacao;
    private Integer estoque;
}
