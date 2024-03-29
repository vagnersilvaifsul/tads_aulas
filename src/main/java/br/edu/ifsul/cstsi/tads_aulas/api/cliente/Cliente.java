package br.edu.ifsul.cstsi.tads_aulas.api.cliente;

import br.edu.ifsul.cstsi.tads_aulas.api.pedido.Pedido;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Collection;

@Entity(name = "clientes")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Cliente {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private String nome;
    private String sobrenome;
    private Byte situacao;
    @OneToMany(mappedBy = "cliente")
    private Collection<Pedido> pedidos;
}
