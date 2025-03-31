package br.edu.ifsul.cstsi.tads_aulas.api.pedido;

import br.edu.ifsul.cstsi.tads_aulas.api.cliente.Cliente;
import br.edu.ifsul.cstsi.tads_aulas.api.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name="pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pagamento;
    private String estado;
    private Date dataCriacao;
    private Date dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;
    @OneToMany(mappedBy = "pedido", fetch = FetchType.EAGER)
    private Collection<Item> items;
    @ManyToOne @JoinColumn(name = "cliente_id", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
}
