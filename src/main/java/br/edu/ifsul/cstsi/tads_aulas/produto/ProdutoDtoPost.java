package br.edu.ifsul.cstsi.tads_aulas.produto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * DTO for {@link Produto}
 */
public record ProdutoDtoPost(String nome, BigDecimal valorDeCompra, BigDecimal valorDeVenda, String descricao,
                             Integer estoque) implements Serializable {
}