package br.edu.ifsul.cstsi.tads_aulas.model;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.Collection;

public class Pedido {
    private Long id;
    private String pagamento;
    private String estado;
    private Date dataCriacao;
    private Date dataModificacao;
    private Byte situacao;
    private BigDecimal totalPedido;
    private Collection<Item> items;
    private Cliente cliente;
}
