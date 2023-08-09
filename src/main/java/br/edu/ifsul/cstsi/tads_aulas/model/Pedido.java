package br.edu.ifsul.cstsi.tads_aulas.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "pedidos", schema = "lpoo_vendas_jdbc", catalog = "")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Pedido {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String pagamento;
    private String estado;
    private Date dataCriacao;
    private Date dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;
    @OneToMany(mappedBy = "pedido")
    private Collection<Iten> itens;
    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id", nullable = false)
    private Cliente cliente;
}
