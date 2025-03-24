package br.edu.ifsul.cstsi.tads_aulas.model;

import java.math.BigDecimal;


public class Item {
    private Long id;
    private BigDecimal quantidade;
    private BigDecimal totalItem;
    private Byte situacao;
    private Pedido pedido;
    private Produto produto;
}
