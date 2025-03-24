package br.edu.ifsul.cstsi.tads_aulas.model;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Byte situacao;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY)
    private Collection<Pedido> pedidos;
}
