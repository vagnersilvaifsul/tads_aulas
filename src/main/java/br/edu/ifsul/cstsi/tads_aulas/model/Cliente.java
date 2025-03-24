package br.edu.ifsul.cstsi.tads_aulas.model;

import java.util.Collection;


public class Cliente {
    private Long id;
    private String nome;
    private String sobrenome;
    private Byte situacao;
    private Collection<Pedido> pedidos;
}
