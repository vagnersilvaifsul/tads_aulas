package br.edu.ifsul.cstsi.tads_aulas.cliente;

import br.edu.ifsul.cstsi.tads_aulas.pedido.Pedido;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Collection;

@Entity(name="Cliente")
@Table(name = "clientes")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class Cliente {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nome;
    private String sobrenome;
    private Byte situacao;
    @OneToMany(mappedBy = "cliente", fetch = FetchType.EAGER)
    private Collection<Pedido> pedidos;
}
