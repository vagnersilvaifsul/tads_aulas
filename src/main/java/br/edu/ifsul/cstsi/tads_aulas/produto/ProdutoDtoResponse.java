package br.edu.ifsul.cstsi.tads_aulas.produto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Produto}
 */
public record ProdutoDtoResponse(String nome, BigDecimal valorDeVenda, String descricao,
                                 Integer estoque) implements Serializable {

    public ProdutoDtoResponse(Produto produto) {
        this(produto.getNome(), produto.getValorDeVenda(), produto.getDescricao(), produto.getEstoque());
    }
}