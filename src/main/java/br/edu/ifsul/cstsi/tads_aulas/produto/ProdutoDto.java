package br.edu.ifsul.cstsi.tads_aulas.produto;

import java.io.Serializable;
import java.math.BigDecimal;

public record ProdutoDto(
        String nome,
        BigDecimal valorDeCompra,
        BigDecimal valorDeVenda,
        String descricao,
        Boolean situacao,
        Integer estoque) implements Serializable {
    public ProdutoDto(Produto produto) {
        this(produto.getNome(), produto.getValorDeCompra(), produto.getValorDeVenda(), produto.getDescricao(), produto.getSituacao(), produto.getEstoque());
    }
}