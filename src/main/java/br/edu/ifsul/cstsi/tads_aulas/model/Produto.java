package br.edu.ifsul.cstsi.tads_aulas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

@Entity
@Table(name = "produtos", schema = "lpoo_vendas_jdbc", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private BigDecimal valorDeCompra;
    private BigDecimal valorDeVenda;
    private String descricao;
    private Byte situacao;
    private Integer quantidade;
    @OneToMany(mappedBy = "produto")
    private Collection<Iten> itens;
}
