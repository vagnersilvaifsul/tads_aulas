package br.edu.ifsul.cstsi.tads_aulas.model;

import jakarta.persistence.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collection;

@Entity
@Table(name = "pedidos")
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
