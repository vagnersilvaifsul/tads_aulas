package br.edu.ifsul.cstsi.tads_aulas.api.produto;

import br.edu.ifsul.cstsi.tads_aulas.api.item.Item;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.util.Collection;

@Entity(name = "produtos")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Produto {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    @NotBlank(message = "O nome não pode ser nulo ou vazio") //verifica se está vazio e estabelece como obrigatório (não pode ser nulo)
    @Size(min = 2, max = 50, message = "Tamanho mínimo de 2 e máximo de 200")
    private String nome;
    @NotNull
    @Min(0)
    private BigDecimal valorDeCompra;
    @NotNull
    @Min(0)
    private BigDecimal valorDeVenda;
    @NotBlank(message = "A descrição não pode ser nula ou vazio") //verifica se está vazio e estabelece como obrigatório (não pode ser nulo)
    @Size(min = 2, max = 255, message = "Tamanho mínimo de 2 e máximo de 255")
    private String descricao;
    @NotNull
    private Boolean situacao;
    @NotNull
    @Min(0)
    private Integer quantidade;
    @OneToMany(mappedBy = "produto")
    private Collection<Item> items;
}
