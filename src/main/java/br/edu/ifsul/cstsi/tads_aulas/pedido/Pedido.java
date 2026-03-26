package br.edu.ifsul.cstsi.tads_aulas.pedido;

import br.edu.ifsul.cstsi.tads_aulas.cliente.Cliente;
import br.edu.ifsul.cstsi.tads_aulas.item.Item;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "pedidos")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pagamento;
    private String estado;
    private LocalDate dataCriacao;
    private LocalDate dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;

    //Associações
    @OneToMany (mappedBy = "pedido")
    private Collection<Item> items;
    @ManyToOne
    private Cliente cliente;
}
