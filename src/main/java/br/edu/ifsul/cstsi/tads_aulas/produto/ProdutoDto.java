package br.edu.ifsul.cstsi.tads_aulas.produto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Produto}
 */
public record ProdutoDto(Long id, String nome, BigDecimal valorDeVenda, String descricao,
                         Integer estoque) implements Serializable {

    public ProdutoDto(Produto produto){
        this(produto.getId(), produto.getNome(), produto.getValorDeVenda(), produto.getDescricao(), produto.getEstoque());
    }
}