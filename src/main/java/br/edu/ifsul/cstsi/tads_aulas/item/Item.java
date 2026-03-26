package br.edu.ifsul.cstsi.tads_aulas.item;

import br.edu.ifsul.cstsi.tads_aulas.pedido.Pedido;
import br.edu.ifsul.cstsi.tads_aulas.produto.Produto;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;

@Entity
@Table(name = "itens")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private BigDecimal quantidade;
    private BigDecimal totalItem;
    private Byte situacao;

    //Associações
    @ManyToOne
    private Pedido pedido;
    @OneToOne
    private Produto produto;
}
