package br.edu.ifsul.cstsi.tads_aulas.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Entity
@Table(name = "pedidos")
@Getter
@Setter
@NoArgsConstructor
public class Pedido {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String pagamento;
    private String estado;
    private Date dataCriacao;
    private Date dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
}