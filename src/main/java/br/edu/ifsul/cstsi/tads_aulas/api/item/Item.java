package br.edu.ifsul.cstsi.tads_aulas.api.item;

import br.edu.ifsul.cstsi.tads_aulas.api.pedido.Pedido;
import br.edu.ifsul.cstsi.tads_aulas.api.produto.Produto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "itens", schema = "lpoo_vendas_jdbc", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Item {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private BigDecimal quantidade;
    private BigDecimal totalItem;
    private Byte situacao;
    @ManyToOne
    @JoinColumn(name = "id_pedido", referencedColumnName = "id", nullable = false)
    private Pedido pedido;
    @ManyToOne
    @JoinColumn(name = "id_produto", referencedColumnName = "id", nullable = false)
    private Produto produto;
}
