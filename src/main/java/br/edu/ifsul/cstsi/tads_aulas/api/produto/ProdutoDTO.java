package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import lombok.Data;
import org.modelmapper.ModelMapper;

import java.math.BigDecimal;

@Data
public class ProdutoDTO {
    private Long id;
    private String nome;
    private BigDecimal valorDeVenda;
    private String descricao;
    private Integer quantidade;

    public static ProdutoDTO create(Produto p){
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(p, ProdutoDTO.class);
    }
}
