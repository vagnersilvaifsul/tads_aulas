package br.edu.ifsul.cstsi.tads_aulas.produto;

import java.math.BigDecimal;

/**
 * DTO for {@link Produto}
 */
public record ProdutoDtoPut(
    String nome,
    String descricao,
    BigDecimal valorDeCompra,
    BigDecimal valorDeVenda,
    Integer estoque,
    Boolean situacao
) {
    public ProdutoDtoPut(Produto produto){
        this(produto.getNome(), produto.getDescricao(), produto.getValorDeCompra(), produto.getValorDeVenda(), produto.getEstoque(), produto.getSituacao());
    }
}
