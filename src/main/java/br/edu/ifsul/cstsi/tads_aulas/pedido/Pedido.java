package br.edu.ifsul.cstsi.tads_aulas.pedido;

import br.edu.ifsul.cstsi.tads_aulas.cliente.Cliente;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

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
    private Date dataCriacao;
    private Date dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;

    @ManyToOne(fetch = FetchType.EAGER)
    private Cliente cliente;
}